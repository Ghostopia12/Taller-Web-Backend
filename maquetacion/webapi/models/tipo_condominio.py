from django.db import models


class TipoCondominio(models.Model):
    nombre_tipo_condominio = models.CharField(max_length=50)


    def __str__(self):
        return self.nombre_tipo_condominio
