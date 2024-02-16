from django.shortcuts import render
from rest_framework import serializers, viewsets

from api.models import Users_rol, Recidencia, AreaComun


# Create your views here.
class Users_rolSerializer(serializers.ModelSerializer):
    class Meta:
        model = Users_rol
        fields = '__all__'


class Users_rolViewSet(viewsets.ModelViewSet):
    queryset = Users_rol.objects.all()
    serializer_class = Users_rolSerializer


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