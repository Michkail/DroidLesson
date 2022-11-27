from django.urls import path, include
from . import views

urlpatterns = [
    path('addTodoItems', views.TodoAPIView.as_view(), name='addTodoItems'),
    path('', views.home, name='home'),
]
