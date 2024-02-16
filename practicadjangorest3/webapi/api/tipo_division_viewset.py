from rest_framework import viewsets, serializers


from webapi.models import TipoDivision


class TipoDivisionSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoDivision
        fields = '__all__'


class TipoDivisionViewSet(viewsets.ModelViewSet):
    serializer_class = TipoDivisionSerializer
    queryset = TipoDivision.objects.all()
