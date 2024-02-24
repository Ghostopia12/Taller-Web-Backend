from django.db import models

from webapi.models import TipoCondominio, TipoDivision


class Condominio(models.Model):
    nombre = models.CharField(max_length=50)
    activo = models.BooleanField(default=False)
    creador = models.IntegerField()

    # Foreign Keys
    tipoCondominioId = models.ForeignKey(
        TipoCondominio,
        on_delete=models.CASCADE,
        related_name="tipos"
    )
    tipoDivisionId = models.ForeignKey(
        TipoDivision,
        on_delete=models.CASCADE,
        related_name="divisiones"
    )

    def __str__(self):
        return self.nombre
