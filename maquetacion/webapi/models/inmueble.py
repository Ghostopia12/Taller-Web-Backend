from django.db import models
from django.db.models.signals import pre_save
from django.dispatch import receiver
from django.utils import timezone

from webapi.models import TipoCondominio, TipoDivision, DivisionManzana, Piso


class Inmueble(models.Model):
    nombre = models.CharField(max_length=50)
    activo = models.BooleanField(default=False)
    superficie = models.IntegerField()
    latitud = models.FloatField()
    longitud = models.FloatField()
    fecha_cambio_activo = models.DateTimeField(null=True, blank=True)
    capacidad = models.IntegerField()
    estado_construccion = models.BooleanField(default=False)
    residente_id = models.IntegerField()

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
@receiver(pre_save, sender=Inmueble)
def actualizar_fecha_cambio_activo(sender, instance, **kwargs):
    if instance.pk is None:  # Si es un nuevo objeto
        instance.fecha_cambio_activo = timezone.now()
    else:
        try:
            inmueble_anterior = Inmueble.objects.get(pk=instance.pk)
            if inmueble_anterior.activo != instance.activo:
                instance.fecha_cambio_activo = timezone.now()
        except Inmueble.DoesNotExist:
            pass
