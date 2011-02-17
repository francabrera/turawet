from django.db import models

# Create your models here.
class User(models.Model):
    name = models.CharField(max_length=128)
    surname = models.CharField(max_length=128)
    
    def __unicode__(self):
        return self.name

class Group(models.Model):
    name = models.CharField(max_length=128)
    #members = models.ManyToManyField(Person, through='Membership')

    def __unicode__(self):
        return self.name