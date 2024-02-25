from django.db import models
from django.db.models.signals import pre_save
from django.dispatch import receiver
from django.utils import timezone
from webapi.models import TipoCondominio, TipoDivision

class Condominio(models.Model):
    nombre = models.CharField(max_length=50)
    activo = models.BooleanField(default=False)
    creador = models.IntegerField()
    latitud = models.FloatField()
    longitud = models.FloatField()
    fecha_cambio_activo = models.DateTimeField(null=True, blank=True)
    capacidad_estacionamiento = models.IntegerField()
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

@receiver(pre_save, sender=Condominio)
def actualizar_fecha_cambio_activo(sender, instance, **kwargs):
    if instance.pk is None:  # Si es un nuevo objeto
        instance.fecha_cambio_activo = timezone.now()
    else:
        try:
            condominio_anterior = Condominio.objects.get(pk=instance.pk)
            if condominio_anterior.activo != instance.activo:
                instance.fecha_cambio_activo = timezone.now()
        except Condominio.DoesNotExist:
            pass
