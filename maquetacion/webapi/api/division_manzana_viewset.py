from rest_framework import viewsets, serializers

from webapi.api import CondominioSimpleSerializer
from webapi.models import DivisionManzana

class DivisionManzanaSerializer(serializers.ModelSerializer):
    condominioId = CondominioSimpleSerializer(read_only=True)
    condominioId_id = serializers.IntegerField(write_only=True)

    class Meta:
        model = DivisionManzana
        fields = '__all__'


class DivisionManzanaViewSet(viewsets.ModelViewSet):
    serializer_class = DivisionManzanaSerializer
    queryset = DivisionManzana.objects.all()
