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


###################################################################

# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
class FormFields(models.Model):
    label = models.CharField(max_length=256)
    order = models.SmallIntegerField()
    section = models.ForeignKey(Section)
    field_group = models.ForeignKey(FieldGroup)
    field_list = models.ForeignKey(FieldList)
    # ORDEN DENTRO DEL GRUPO AQUI --> Problema muchos nulos
    
    class Meta:
        unique_together = ('name', 'section', 'order')
            
    def __unicode__(self):
        return self.label

###################################################################
# Falta decir los nulos en todas las clases
###################################################################

class Instance(models.Model):
    creation_date = models.DateField()
    modification_date = models.DateField()
    signature = models.CharField(max_length=128) # TO-DO 
    form = models.ForeignKey(Form)
    
    def __unicode__(self):
        return self.id
    
    
### FALTA HERENCIA
class InstanceFields(models.Model):
    instance = models.ForeignKey(Instance)
    form_fields = models.ForeignKey(FormFields)
    
    def __unicode__(self):
        return self.label
