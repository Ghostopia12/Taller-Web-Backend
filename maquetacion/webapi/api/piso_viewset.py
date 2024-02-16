from rest_framework import viewsets, serializers

from webapi.api import DivisionBloqueSimpleSerializer
from webapi.models import Piso


class PisoSerializer(serializers.ModelSerializer):
    bloqueId = DivisionBloqueSimpleSerializer(read_only=True)
    bloqueId_id = serializers.IntegerField(write_only=True)
    class Meta:
        model = Piso
        fields = '__all__'


class PisoViewSet(viewsets.ModelViewSet):
    serializer_class = PisoSerializer
    queryset = Piso.objects.all()
