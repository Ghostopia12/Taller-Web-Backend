from django.db import models


class TipoDivision(models.Model):
    nombre_division = models.CharField(max_length=50)

    def __str__(self):
        return self.nombre_division