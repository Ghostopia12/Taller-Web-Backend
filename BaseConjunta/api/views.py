from django.shortcuts import render
from rest_framework import serializers, viewsets, status
from rest_framework.decorators import action
from rest_framework.response import Response

from api.models import Users_rol, Recidencia, AreaComun


# Create your views here.
class Users_rolSerializer(serializers.ModelSerializer):
    class Meta:
        model = Users_rol
        fields = '__all__'


class Users_rolViewSet(viewsets.ModelViewSet):
    queryset = Users_rol.objects.all()
    serializer_class = Users_rolSerializer

    def create(self, request, *args, **kwargs):
        for rol in request.data['roles']:
            data = {
                "user_id": request.data['user_id'],
                "rol_id": rol
            }
            serializer = Users_rolSerializer(data=data)
            serializer.is_valid(raise_exception=True)
            serializer.save()
        return Response({
            "detalle": "Se guardo correctamente.",
            "status_code": 201
        }, status=status.HTTP_201_CREATED)

    @action(detail=True, methods=['get'], url_path="roles-usuario",
            name="Lista de roles por usuario")
    def roles_x_usuario(self, request, pk=None):
        if pk is None:
            return Response({
                "detalle": "No encontrado.",
                "status_code": 404
            }, status=status.HTTP_404_NOT_FOUND)
        try:
            queryset = Users_rol.objects.all().filter(user_id=pk)
            serializer_class = Users_rolSerializer(queryset, many=True, read_only=True)
            return Response(serializer_class.data)
        except Users_rol.DoesNotExist:
            return Response({
                "detalle": "No se encontro.",
                "status_code": 404
            }, status=status.HTTP_404_NOT_FOUND)

    @action(detail=False, methods=['post'], url_path="user-all",
            name="Lista de usuarios_roles ordenados por el user id")
    def user_all(self, request, pk=None):
        try:
            queryset = Users_rol.objects.all().order_by('user_id')
            serializer_class = Users_rolSerializer(queryset, many=True, read_only=True)
            return Response(serializer_class.data)
        except Users_rol.DoesNotExist:
            return Response({
                "detalle": "No se encontro.",
                "status_code": 404
            }, status=status.HTTP_404_NOT_FOUND)

    @action(detail=False, methods=['post'], url_path="actualizar-roles",
            name="Actualizar los roles de un usuario")
    def user_all(self, request, pk=None):
        registros_a_eliminar = Users_rol.objects.filter(id_user=request.data['user_id'])
        registros_a_eliminar.delete()
        for rol in request.data['roles']:
            data = {
                "user_id": request.data['user_id'],
                "rol_id": rol
            }
            serializer = Users_rolSerializer(data=data)
            serializer.is_valid(raise_exception=True)
            serializer.save()
        return Response({
            "detalle": "Se guardo correctamente.",
            "status_code": 201
        }, status=status.HTTP_201_CREATED)


class RecidenciaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Recidencia
        fields = '__all__'


class RecidenciaViewSet(viewsets.ModelViewSet):
    queryset = Recidencia.objects.all()
    serializer_class = RecidenciaSerializer


class AreaComunSerializer(serializers.ModelSerializer):
    class Meta:
        model = AreaComun
        fields = '__all__'


class AreaComunViewSet(viewsets.ModelViewSet):
    queryset = AreaComun.objects.all()
    serializer_class = AreaComunSerializer
