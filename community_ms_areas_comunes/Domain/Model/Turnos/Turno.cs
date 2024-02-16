using Shared.Core;

namespace Domain.Model.Turnos
{
    public class Turno:AggregateRoot<Guid>
    {
        public TimeOnly Inicio { get; private set; }
        public TimeOnly Fin { get; private set; }
        public Turno(TimeOnly inicio, TimeOnly fin)
        {

            Id = Guid.NewGuid();
            Inicio = inicio; 
            Fin = fin;
               
        }
        public Turno() { }
    }
}
