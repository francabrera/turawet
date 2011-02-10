from django.db import models

# Create your models here.

# ---------------------------------------------------------
# FORM
# ---------------------------------------------------------
class Form(models.Model):
    name = models.CharField(max_length=256)
    version = models.IntegerField()
    xml = models.XMLField(schema_path='NO OLVIDAR')

    class Meta:
        unique_together = ('name', 'version')
    
    def __unicode__(self):
        return self.name


# ---------------------------------------------------------
# SECTION
# ---------------------------------------------------------
class Section(models.Model):
    name = models.CharField(max_length=128)
    order = models.SmallIntegerField()
    form = models.ForeignKey(Form)
    
    class Meta:
        unique_together = ('name', 'form')

    def __unicode__(self):
        return self.name


# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
class FormFields(models.Model):
    label = models.CharField(max_length=256)
    order = models.CharField(max_length=128)
    section = models.ForeignKey(Section)
    
    def __unicode__(self):
        return self.label


class Instance(models.Model):
    id = models.CharField(max_length=128)
    creation_date = models.CharField(max_length=128)
    signature = models.CharField(max_length=128)    
    form = models.ForeignKey(Form)
    
    def __unicode__(self):
        return self.id
    
    
class InstanceFields(models.Model):
    instance = models.ForeignKey(Instance)
    form_fields = models.ForeignKey(FormFields)
    
    def __unicode__(self):
        return self.label
