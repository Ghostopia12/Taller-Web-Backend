using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UsuariosTallerWeb.Domain.Models
{
    public class UserRole
    {
        [Key]
        [Column(Order = 1)]
        public int UsuarioId { get; set; }

        [Key]
        [Column(Order = 2)]
        public int RolId { get; set; }

        [ForeignKey("UsuarioId")]
        public virtual User Usuario { get; set; }

        [ForeignKey("RolId")]
        public virtual Rol Rol { get; set; }
    }
}
