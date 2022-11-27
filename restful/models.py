from django.db import models


class TodoModel(models.Model):
    toTitle = models.CharField(max_length=120, blank=False, default='New Todo')
    toDesc = models.TextField(blank=True, default='')
    