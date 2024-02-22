using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UsuariosTallerWeb.Application.Services.Interfaces;
using UsuariosTallerWeb.Domain.Models;

namespace UsuariosTallerWeb.Application.Services
{
    public class RoleService : IRoleService
    {
        public Task<IEnumerable<User>?> GetRoles()
        {
            throw new NotImplementedException();
        }
    }
}
