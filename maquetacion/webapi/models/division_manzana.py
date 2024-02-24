from django.db import models

from webapi.models import TipoCondominio, TipoDivision, Condominio


class DivisionManzana(models.Model):

    cantidad_lotes = models.IntegerField()

    # Foreign Keys
    condominioId = models.ForeignKey(
        Condominio,
        on_delete=models.CASCADE,
        related_name="condominioId_manzana"
    )

    def __str__(self):
        return self.cantidad_lotes
