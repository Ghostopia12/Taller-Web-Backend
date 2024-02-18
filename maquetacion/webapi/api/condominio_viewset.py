from rest_framework import viewsets, serializers

from webapi.api import TipoCondominioSimpleSerializer, TipoDivisionSimpleSerializer
from webapi.models import Condominio


class CondominioSerializer(serializers.ModelSerializer):
    tipoCondominioId = TipoCondominioSimpleSerializer(read_only=True)
    tipoCondominioId_id = serializers.IntegerField(write_only=True)
    tipoDivisionId = TipoDivisionSimpleSerializer(read_only=True)
    tipoDivisionId_id = serializers.IntegerField(write_only=True)
    class Meta:
        model = Condominio
        fields = '__all__'


class CondominioViewSet(viewsets.ModelViewSet):
    serializer_class = CondominioSerializer
    queryset = Condominio.objects.all()
