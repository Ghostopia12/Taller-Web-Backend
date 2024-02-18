from rest_framework import viewsets, serializers

from webapi.api import CondominioSimpleSerializer
from webapi.models import DivisionBloque


class DivisionBloqueSerializer(serializers.ModelSerializer):
    condominioId = CondominioSimpleSerializer(read_only=True)
    condominioId_id = serializers.IntegerField(write_only=True)

    class Meta:
        model = DivisionBloque
        fields = '__all__'


class DivisionBloqueViewSet(viewsets.ModelViewSet):
    serializer_class = DivisionBloqueSerializer
    queryset = DivisionBloque.objects.all()
