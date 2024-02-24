from django.db import models

from webapi.models import TipoCondominio, TipoDivision, DivisionManzana, Piso


class Inmueble(models.Model):
    nombre = models.CharField(max_length=50)
    activo = models.BooleanField(default=False)
    superficie = models.IntegerField(default=False)

    # Foreign Keys


    manzanaId = models.ForeignKey(
        DivisionManzana,
        on_delete=models.CASCADE,
        related_name="manzanaId",
        null=True
    )
    pisoId = models.ForeignKey(
        Piso,
        on_delete=models.CASCADE,
        related_name="pisoId",
        null=True
    )

    def __str__(self):
        return self.nombre
