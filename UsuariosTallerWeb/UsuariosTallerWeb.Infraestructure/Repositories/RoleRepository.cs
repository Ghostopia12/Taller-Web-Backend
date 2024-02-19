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
    public class RoleRepository : IRoleRepository
    {


        private readonly AuthenticationContext _context;

        public RoleRepository(AuthenticationContext context)
        {
            _context = context;
        }


        public Task<Rol?> CreateRol(Rol rol)
        {
            throw new NotImplementedException();
        }

        public Task<Rol?> DeleteRol(Rol id)
        {
            throw new NotImplementedException();
        }

        public async Task<Rol?> GetRolById(int id)
        {
            var rol =  _context.Roles.FirstOrDefaultAsync(x => x.Id == id);
            return rol.Result;

        }

        public async Task<IEnumerable<Rol>?> GetRoles()
        {
            var roles = await _context.Roles
                                      .AsNoTracking()
                                      .Select(x => new Rol { Id = x.Id, Nombre = x.Nombre }) // Proyecta solo Id y Nombre
                                      .ToListAsync();

            return roles;
        }

        public Task<Rol?> UpdateRol(int id, Rol rol)
        {
            throw new NotImplementedException();
        }
    }
}
