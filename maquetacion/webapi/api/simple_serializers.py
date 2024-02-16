from rest_framework import serializers

from webapi.models import Condominio, TipoCondominio, TipoDivision, Piso, DivisionBloque, DivisionManzana


class CondominioSimpleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Condominio
        fields = ['id', 'nombre', 'tipoCondominioId','tipoDivisionId']

class TipoCondominioSimpleSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoCondominio
        fields = ['id', 'nombre_tipo_condominio']

class TipoDivisionSimpleSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoDivision
        fields = ['id', 'nombre_division']

class DivisionBloqueSimpleSerializer(serializers.ModelSerializer):
    class Meta:
        model = DivisionBloque
        fields = ['id', 'cantidad_pisos', 'nombre', 'condominioId']

class PisoSimpleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Piso
        fields = ['id', 'numero', 'bloqueId']

class DivisionManzanaSimpleSerializer(serializers.ModelSerializer):
    class Meta:
        model = DivisionManzana
        fields = ['id', 'cantidad_lotes','condominioId']