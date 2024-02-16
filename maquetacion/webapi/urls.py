from django.urls import path, include
from rest_framework import routers
from rest_framework.authtoken import views

from webapi.api import CondominioViewSet, TipoCondominioViewSet, TipoDivisionViewSet, DivisionBloqueViewSet
from webapi.api.division_manzana_viewset import DivisionManzanaViewSet
from webapi.api.inmueble_viewset import InmuebleViewSet
from webapi.api.piso_viewset import PisoViewSet

router = routers.DefaultRouter()
router.register(r'condominios', CondominioViewSet)
router.register(r'tipo_condominios', TipoCondominioViewSet)
router.register(r'tipo_divisiones', TipoDivisionViewSet)
router.register(r'tipo_division_manzana', DivisionManzanaViewSet)
router.register(r'tipo_division_bloque', DivisionBloqueViewSet)
router.register(r'piso', PisoViewSet)
router.register(r'inmuebles', InmuebleViewSet)



urlpatterns = [
    path('', include(router.urls)),
]
