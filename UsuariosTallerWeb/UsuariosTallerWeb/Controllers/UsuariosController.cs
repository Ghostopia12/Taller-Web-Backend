using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System.Data;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;
using UsuariosTallerWeb.Application.Services.Interfaces;
using UsuariosTallerWeb.Domain.DTOs;
using UsuariosTallerWeb.Domain.Models;

namespace UsuariosTallerWeb.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuariosController : ControllerBase
    {
        private static readonly HttpClient httpClient = new HttpClient()
        {
            BaseAddress = new Uri("http://localhost:7777/api/"),
        };

        private readonly IUserService _userService;
        private IConfiguration _config;
        public UsuariosController(IUserService userService, IConfiguration config)
        {
            _userService = userService;
            _config = config;
        }

        [HttpGet]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult GetAll()
        {
            var users = _userService.GetUsers();
            if (users == null)
            {
                return NoContent();
            }
            return Ok(users);
        }

        [HttpPost]
        [ProducesResponseType(StatusCodes.Status201Created)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> Create([FromBody] UserDto newUser)
        {
            if (newUser == null)
            {
                return BadRequest("Invalid user data");
            }

            var createdUser = await _userService.CreateUser(newUser);

            if (createdUser == null)
            {
                return BadRequest("Failed to create user");
            }

            var userList = newUser.Roles.ToList();

            var jsonContent = new StringContent(
                JsonSerializer.Serialize(new
                {
                    user_id = createdUser.Id,
                    roles = userList 
                }),
                Encoding.UTF8,
                "application/json"
            );


            using var response = await httpClient.PostAsync(
                "users-rol/",
                jsonContent
            );

            response.EnsureSuccessStatusCode();

            var jsonResponse = await response.Content.ReadAsStringAsync();
            Console.WriteLine($"{jsonResponse}\n");

            return CreatedAtRoute(nameof(GetUser), new { userId = createdUser.Id }, createdUser);
        }

        [HttpGet("{userId}", Name = nameof(GetUser))]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult GetUser(int userId)
        {
            var user = _userService.GetUserById(userId);
            if (user == null)
            {
                return NotFound();
            }
            return Ok(user);
        }

        [HttpPost("/authenticate")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public IActionResult AuthenticateUser(LoginDto user)
        {
            if (user == null)
            {
                return BadRequest();
            }

            var authenticatedUser = _userService.AuthenticateUser(user);

            if (authenticatedUser.Result == null)
            {
                return BadRequest("Invalid username or password");
            }

            var roleList = authenticatedUser.Result.Roles.Select(r => r.Nombre).ToList();

            var token = GenerateJwtToken(authenticatedUser.Result, roleList);

            return Ok(new { Token = token });
        }

        private string GenerateJwtToken(User user, List<string>  roleList)
        {

            var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_config["Jwt:Key"]));
            var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);


            var token = new JwtSecurityToken(_config["Jwt:Issuer"], _config["Jwt:Audience"], [
                new Claim(ClaimTypes.NameIdentifier, user.Username),
                new Claim(ClaimTypes.Email, user.Email),
                new Claim(ClaimTypes.Role, string.Join(",", roleList))
                ], expires: DateTime.UtcNow.AddHours(1),
                signingCredentials: credentials
                );
            return new JwtSecurityTokenHandler().WriteToken(token);

         
        }

       

    }


}

