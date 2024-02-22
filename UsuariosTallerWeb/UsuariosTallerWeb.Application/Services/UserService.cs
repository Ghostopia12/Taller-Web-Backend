using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using UsuariosTallerWeb.Application.Services.Interfaces;
using UsuariosTallerWeb.Domain.DTOs;
using UsuariosTallerWeb.Domain.Models;
using UsuariosTallerWeb.Infraestructure.Repositories;
using UsuariosTallerWeb.Infraestructure.Repositories.Interfaces;
using BCrypt.Net;

namespace UsuariosTallerWeb.Application.Services
{
    public class UserService : IUserService
    {
        private readonly IUserRepository _userRepository;
        private readonly IRoleRepository _roleRepository;

        public UserService(IUserRepository userRepository, IRoleRepository roleRepository)
        {
            ArgumentNullException.ThrowIfNull(userRepository);
            _userRepository = userRepository;
            _roleRepository = roleRepository;
        }
        public async Task<User> CreateUser(UserDto userDto)
        {
            if (userDto == null)
            {
                throw new ArgumentNullException(nameof(userDto));
            }
            List<int> emptyList = new List<int> { 0 };

            if (userDto.Roles.SequenceEqual(emptyList))
            {
                //3 es residente
                userDto.Roles = new List<int> { 3 };
            }

            List<Rol> roles = await GetRolesForUser(userDto.Roles);

            string hashedPassword = BCrypt.Net.BCrypt.HashPassword(userDto.Password);

            var user = new User
            {
                Name = userDto.Name,
                Username = userDto.Username,
                Password = hashedPassword,
                Email = userDto.Email,
                Roles = roles
            };

            await _userRepository.CreateUser(user);

            return user;
        }

        public Task<User> DeleteUser(int id)
        {
            throw new NotImplementedException();
        }

        public Task<User> GetUserById(int id)
        {
            throw new NotImplementedException();
        }

        public async Task<IEnumerable<User>?> GetUsers()
        {
            var users = await _userRepository.GetUsers();
            return users!;
        }

        public async Task<User> UpdateUser(User user)
        {
            var id = user.Id;
            var updatedUser = await _userRepository.UpdateUser(id, user);
            return updatedUser;
        }




        public async Task<User?> AuthenticateUser(LoginDto loginUser)
        {
            var user = await _userRepository.GetUserByUsername(loginUser.Username);
            if (user == null)
            {
                return null;
            }

            if (BCrypt.Net.BCrypt.Verify(loginUser.Password, user.Password))
            {
                return user;
            }
            else
            {
                return null;
            }
        }

       
        private async Task<List<Rol>> GetRolesForUser(List<int> roleIds)
        {
            var roles = new List<Rol>();
            foreach (var roleId in roleIds)
            {
                var rol = await _roleRepository.GetRolById(roleId);
                if (rol != null)
                {
                    roles.Add(rol);
                }
            }
            return roles;
        }

        public async Task<IEnumerable<Rol>?> GetRoles()
        {
            var roles = await _roleRepository.GetRoles();
            return roles;
        }
       
    }
}
