from rest_framework import viewsets, serializers

from webapi.api.simple_serializers import DivisionManzanaSimpleSerializer, PisoSimpleSerializer
from webapi.models.inmueble import Inmueble

class InmuebleSerializer(serializers.ModelSerializer):
    manzanaId = DivisionManzanaSimpleSerializer(read_only=True)
    manzanaId_id = serializers.IntegerField(write_only=True, required=False)
    pisoId = PisoSimpleSerializer(read_only=True)
    pisoId_id = serializers.IntegerField(write_only=True, required=False)
    class Meta:
        model = Inmueble
        fields = '__all__'


class InmuebleViewSet(viewsets.ModelViewSet):
    serializer_class = InmuebleSerializer
    queryset = Inmueble.objects.all()
