using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UsuariosTallerWeb.Domain.Models;

namespace UsuariosTallerWeb.Infraestructure.Repositories.Interfaces
{
    public interface IRoleRepository
    {
        Task<IEnumerable<Rol>?> GetRoles();



        Task<Rol?> GetRolById(int id);
        Task<Rol?> CreateRol(Rol rol);
        Task<Rol?> UpdateRol(int id, Rol rol);
        Task<Rol?> DeleteRol(Rol id);
    }
}
