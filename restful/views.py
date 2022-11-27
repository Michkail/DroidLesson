from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from .apis.models import TodoModelSerializer


def home(request):
    return HttpResponse('API is working')


class TodoAPIView(APIView):
    def post(self, request):
        data = request.data
        serializer = TodoModelSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=200)

        return Response(serializer.errors, status=400)
