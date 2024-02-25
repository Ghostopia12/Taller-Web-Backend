from rest_framework import viewsets, serializers, status
from rest_framework.decorators import action
from rest_framework.response import Response

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

    @action(detail=True, methods=['post'], url_path="residencia-usuario",
            name="Lista de recidencias por usuario")
    def reciencia_x_usuario(self, request, pk=None):
        if pk is None:
            return Response({
                "detalle": "No encontrado.",
                "status_code": 404
            }, status=status.HTTP_404_NOT_FOUND)
        try:
            queryset = Inmueble.objects.all().filter(residente_id=pk)
            serializer_class = InmuebleSerializer(queryset, many=True, read_only=True)
            return Response(serializer_class.data)
        except Inmueble.DoesNotExist:
            return Response({
                "detalle": "No se encontro.",
                "status_code": 404
            }, status=status.HTTP_404_NOT_FOUND)