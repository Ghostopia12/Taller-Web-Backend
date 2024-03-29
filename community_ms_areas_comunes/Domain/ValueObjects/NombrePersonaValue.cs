﻿using Shared.Core;
using Shared.Rules;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.ValueObjects
{
    public record NombrePersonaValue : ValueObject
    {
        public string Nombre { get; init; }

        public NombrePersonaValue(string nombre)
        {
            CheckRule(new StringNotNullOrEmptyRule(nombre));
            if (nombre.Length > 40)
            {
                throw new BussinessRuleValidationException("Nombre no puede tener mas de 50 caracteres");
            }
            Nombre = nombre;
        }

        public static implicit operator string(NombrePersonaValue value)
        {
            return value.Nombre;
        }

        public static implicit operator NombrePersonaValue(string nombre)
        {
            return new NombrePersonaValue(nombre);
        }
    }
}
