from django.db import models

from webapi.models import TipoCondominio, TipoDivision, Condominio, DivisionBloque


class Piso(models.Model):
    numero = models.IntegerField()

    # Foreign Keys
    bloqueId = models.ForeignKey(
        DivisionBloque,
        on_delete=models.CASCADE,
        related_name="bloqueId_piso"
    )

    def __str__(self):
        return self.cantidad_lotes
