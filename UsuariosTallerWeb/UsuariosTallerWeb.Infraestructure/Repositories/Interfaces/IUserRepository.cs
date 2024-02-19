using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UsuariosTallerWeb.Domain.Models;

namespace UsuariosTallerWeb.Infraestructure.Repositories.Interfaces
{
    public interface IUserRepository
    {
        Task<IEnumerable<User>?> GetUsers();

        Task<User?> GetUserById(int id);
        Task<User?> CreateUser(User User);
        Task<User?> UpdateUser(int id, User User);
        Task<User?> DeleteUser(User id);
        Task<User?> GetUserByUsername(string name);
            
    }
}
