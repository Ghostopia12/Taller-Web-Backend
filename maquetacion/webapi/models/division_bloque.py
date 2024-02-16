from django.db import models

from webapi.models import TipoCondominio, TipoDivision, Condominio


class DivisionBloque(models.Model):
    cantidad_pisos = models.IntegerField()
    nombre = models.CharField(max_length=50)

    # Foreign Keys
    condominioId = models.ForeignKey(
        Condominio,
        on_delete=models.CASCADE,
        related_name="condominioId_bloque"
    )

    def __str__(self):
        return self.nombre
