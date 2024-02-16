from django.urls import path, include
from rest_framework import routers

from .views import Users_rolViewSet, RecidenciaViewSet, AreaComunViewSet

router = routers.DefaultRouter()
router.register(r'users_rol', Users_rolViewSet)
router.register(r'recidencia', RecidenciaViewSet)
router.register(r'areacomun', AreaComunViewSet)

urlpatterns = [
    path('', include(router.urls))
]