using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UsuariosTallerWeb.Domain.Models;

namespace UsuariosTallerWeb.Application.Services.Interfaces
{
    public interface IRoleService
    {
        Task<IEnumerable<User>?> GetRoles();
    }
}
