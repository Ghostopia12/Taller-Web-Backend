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

        public async Task<User?> UpdateUser(int id, User user)
        {
            var existingUser = await _context.Users
                                              .Include(u => u.Roles) // Cargar la relación de roles
                                              .FirstOrDefaultAsync(u => u.Id == id);

            if (existingUser == null)
            {
                return null; // Usuario no encontrado
            }

            // Actualizar propiedades del usuario existente
            existingUser.Name = user.Name;
            existingUser.Username = user.Username;
            existingUser.Email = user.Email;
            existingUser.Roles.Clear();
            foreach (var role in user.Roles)
            {
                var existingRole = await _context.Roles.FirstOrDefaultAsync(r => r.Id == role.Id);
                if (existingRole != null)
                {
                    existingUser.Roles.Add(existingRole);
                }
            }

            await _context.SaveChangesAsync(); // Guardar cambios en la base de datos

            return existingUser;
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
