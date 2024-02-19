using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UsuariosTallerWeb.Domain.Models;
using UsuariosTallerWeb.Infraestructure.Context;
using UsuariosTallerWeb.Infraestructure.Repositories.Interfaces;

namespace UsuariosTallerWeb.Infraestructure.Repositories
{
    public class UserRepository : IUserRepository
    {

        private readonly AuthenticationContext _context;

        public UserRepository(AuthenticationContext context)
        {
            _context = context;
        }

        public async Task<User?> CreateUser(User user)
        {
            if (user == null)
            {
                throw new ArgumentNullException(nameof(user));
            }

            var existingUser = await _context.Users.AsNoTracking().FirstOrDefaultAsync(u => u.Username == user.Username);
            if (existingUser != null)
            {
                throw new InvalidOperationException("El nombre de usuario ya está en uso.");
            }

            _context.Users.Add(user);
            await _context.SaveChangesAsync();

            return user;
        }

        public Task<User?> DeleteUser(User id)
        {
            throw new NotImplementedException();
        }

        public Task<User?> GetUserById(int id)
        {
            throw new NotImplementedException();
        }

        public async Task<IEnumerable<User>?> GetUsers()
        {
            var users =  _context.Users
                                       .Include(u => u.Roles) 
                                       .AsNoTracking()
                                       .ToListAsync();

            return users.Result;
        }

        public Task<User?> UpdateUser(int id, User User)
        {
            throw new NotImplementedException();
        }

        public async Task<User?> GetUserByUsername(string username)
        {
            var user = _context.Users
                                     .Include(u => u.Roles) // Cargar la relación de roles
                                     .FirstOrDefaultAsync(x => x.Username == username);

            return user.Result;
        }
    }
}
