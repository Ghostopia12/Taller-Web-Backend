using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UsuariosTallerWeb.Domain.Models
{
    public class User
    {
        public int Id { get; set; }


        [Required]
        [StringLength(50)]
        public string Name { get; set; }

        [Required]
        [StringLength(50)]
        public string Username { get; set; }

        public string Password { get; set; }

        public string Email { get; set; }


        public virtual ICollection<Rol> Roles { get; set; }

    }
}
