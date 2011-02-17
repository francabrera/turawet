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
class FieldList(models.Model):
    label = models.CharField(max_length=256)
         
    def __unicode__(self):
        return self.label

# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
class FieldGroup(models.Model):
    label = models.CharField(max_length=256)
    list = models.OneToOneField(FieldList)
    # ORDEN DENTRO DEL GRUPO AQUI --> Problema muchos nulos
            
    def __unicode__(self):
        return self.label

###################################################################

# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
class FormFields(models.Model):
    label = models.CharField(max_length=256)
    # Section
    section_order = models.SmallIntegerField()
    section = models.ForeignKey(Section)
    # Groups
    field_group = models.ForeignKey(FieldGroup)
    field_group_order = models.SmallIntegerField()
    # ORDEN DENTRO DEL GRUPO AQUI --> Problema muchos nulos
    
    class Meta:
        unique_together = ('label', 'section', 'section_order')
            
    def __unicode__(self):
        return self.label

###################################################################
# Falta decir los nulos en todas las clases
###################################################################

# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
class Instance(models.Model):
    creation_date = models.DateField()
    modification_date = models.DateField()
    signature = models.CharField(max_length=128) # TO-DO 
    form = models.ForeignKey(Form)
    
    def __unicode__(self):
        return self.form.label + " - " + self.creation_date
    
# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
class InstanceFields(models.Model):
    instance = models.ForeignKey(Instance)
    instance_order = models.SmallIntegerField()
    form_fields = models.ForeignKey(FormFields)
    
    def __unicode__(self):
        return self.form_fields.label

# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
class ImageFields(InstanceFields):
    value = models.ImageField(upload_to = '/')
    #value = models.ImageField()#(verbose_name, name, width_field, height_field)

    def getImage(self):
        return self.value

# ---------------------------------------------------------
# FORMFIELDS
# -------------------------------------------------------
class TextFields(InstanceFields):
    value = models.CharField(max_length=128) # TO-DO
    
    def getText(self):
        return self.value
# ---------------------------------------------------------
# FORMFIELDS
# ---------------------------------------------------------
#class TextAreaFields(TextFields):
    #value = models.TextField()
    