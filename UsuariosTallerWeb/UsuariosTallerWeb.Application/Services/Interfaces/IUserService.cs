using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UsuariosTallerWeb.Domain.DTOs;
using UsuariosTallerWeb.Domain.Models;

namespace UsuariosTallerWeb.Application.Services.Interfaces
{
    public interface IUserService
    {
        Task<IEnumerable<User>?> GetUsers();
        Task<IEnumerable<Rol>?> GetRoles();
        Task<User> GetUserById(int id);
        Task<User> CreateUser(UserDto user);
        Task<User> UpdateUser(User user);
        Task<User> DeleteUser(int id);
        Task<User?> AuthenticateUser(LoginDto loginUser);

    }
}
