from rest_framework import serializers
from restful.models import TodoModel


class TodoModelSerializer(serializers.ModelSerializer):
    class Meta:
        model = TodoModel
        fields = '__all__'
