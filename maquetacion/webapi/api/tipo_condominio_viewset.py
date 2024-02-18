from rest_framework import viewsets, serializers


from webapi.models import TipoCondominio


class TipoCondominioSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoCondominio
        fields = '__all__'


class TipoCondominioViewSet(viewsets.ModelViewSet):
    serializer_class = TipoCondominioSerializer
    queryset = TipoCondominio.objects.all()
