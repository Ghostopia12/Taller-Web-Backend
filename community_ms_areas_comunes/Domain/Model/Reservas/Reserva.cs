using Domain.Model.Turnos;
using Shared.Core;

namespace Domain.Model.Reservas
{
    public class Reserva: AggregateRoot<Guid>
    {
        public Guid AreaComunId { get; private set; }
        public Guid ResidenteId { get; private set; }
        public DateTime Inicio { get; private set; }
        public DateTime Fin { get; private set; }
        public EstadoReserva Estado { get; private set; }


        public Reserva (Guid areaComunId, Guid residenteId,DateTime inicio, DateTime fin)
        {
            if (areaComunId == Guid.Empty)
            {
                throw new BussinessRuleValidationException("El areaComunId es inválido.");
            }
            if (residenteId == Guid.Empty)
            {
                throw new BussinessRuleValidationException("El residenteId es inválido.");
            }

            Id = Guid.NewGuid();
            AreaComunId = areaComunId;
            ResidenteId = residenteId;
            Inicio = inicio;
            Fin = fin;
            Estado = EstadoReserva.Solicitado;
        }
        public Reserva() { }

        public void editarReserva(Guid areaComunId, DateTime inicio, DateTime fin)
        {
            if (areaComunId == Guid.Empty)
            {
                throw new BussinessRuleValidationException("El areaComunId es inválido.");
            }
            Inicio = inicio;
            Fin = fin;
        }
        public void aceptarReserva()
        {
            if (Estado == EstadoReserva.Solicitado)
            {
                Estado = EstadoReserva.Aceptado;
            }
            if (Estado == EstadoReserva.Aceptado)
            {
                throw new BussinessRuleValidationException($"La reserva ya fue aceptada. Estado actual: {Estado}");
            }
            throw new BussinessRuleValidationException($"La reserva no puede ser aceptada. Estado actual: {Estado}");
        }

        public void rechazarReserva()
        {
            if (Estado == EstadoReserva.Solicitado)
            {
                Estado = EstadoReserva.Rechazado;
            }
            if (Estado == EstadoReserva.Rechazado)
            {
                throw new BussinessRuleValidationException($"La reserva ya fue rechazada. Estado actual: {Estado}");
            }
            throw new BussinessRuleValidationException($"La reserva no puede ser rechazada. Estado actual: {Estado}");
        }

    }
}
