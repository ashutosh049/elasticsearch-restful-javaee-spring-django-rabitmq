from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^hello/', views.hello),
    url(r'^newfile/', views.newFile),
    url(r'^shortcut/', views.shortcut),
    url(r'^systemSetting/', views.systemSetting),
]
