"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

# Imports
from django.db import models
from django.contrib.contenttypes.models import ContentType

#from django.core.management.validation import max_length

#--------------------------------------
# Models classes
#--------------------------------------

class Form(models.Model):
    """Class: `Form`. 
       :param name: form name. 
       :param version: form version.
       :param xml: form XML.
       :todo: Add the schema_path to the XML"""
    name = models.CharField(max_length = 256)
    version = models.SmallIntegerField()
    xml = models.CharField(max_length = 16192)
    active = models.BooleanField()
    geolocalized = models.BooleanField()

    class Meta:
        unique_together = ('name', 'version')
        ordering = ['name']

    def __unicode__(self):
        return unicode(self.name)



class Section(models.Model):
    """Class: `Section`. 
       :param name: section name. 
       :param order: section order inside a form.
       :param form: references the form in which this section is."""
    name = models.CharField(max_length = 128)
    order = models.SmallIntegerField()
    form = models.ForeignKey(Form)

    class Meta:
        unique_together = ('name', 'form')
        ordering = ['form']

    def __unicode__(self):
        return unicode(self.name)



class FieldGroup(models.Model):
    """Class: `FieldGroup`. 
       :param label: Field-group label in the form.
       :param multiple: If it is a list of elements"""
    label = models.CharField(max_length = 256)
    multiple = models.BooleanField()
    required = models.BooleanField()

    def __unicode__(self):
        return unicode(self.label)



class FormField(models.Model):
    """Class: `FormField`. 
       :param label: Field label in the form.
       :param section_order: The order of the field in the current section.
       :param section: The current section.
       :param field_group: A Field could belong to a group.
       :param field_group_order: The order of the field in the group (if any).
       :param type: Normal or special type
       :param required: True/False
       :attention The field_group_order is an attribute here
                  because Django that not allow us to have
                  an attribute in a 1:N relationship.
                  The problem is that it will have a lot of null values
        :attention A Field could not belong to a group"""
    label = models.CharField(max_length = 256)
    # Section
    section_order = models.SmallIntegerField()
    section = models.ForeignKey(Section)
    # Groups
    field_group = models.ForeignKey(FieldGroup, null = True)
    field_group_order = models.SmallIntegerField(null = True)
    type = models.CharField(max_length = 128)
    required = models.BooleanField()

    class Meta:
        unique_together = ('section', 'section_order')
        ordering = ['section']

    def __unicode__(self):
        return unicode(self.label)


class FieldOption(models.Model):
    """Class: `FieldOption`.
       :param label: Option label in the field.
       :param value: Value of the option
       :param form_field: The form field which is related to this option """
    label = models.CharField(max_length = 256)
    value = models.CharField(max_length = 128)
    # Link
    form_field = models.ForeignKey(FormField, null = True)

    class Meta:
        unique_together = ('label', 'form_field')
        ordering = ['form_field']
        
    def __unicode__(self):
        return unicode(self.label)


class Property(models.Model):
    """Class: `Property`. 
       :param name: Property name for the field.
       :param value: Value of the property
       :param form_field: The form field which is related to this option """
    name = models.CharField(max_length = 256)
    value = models.CharField(max_length = 128)
    # Link
    form_field = models.ForeignKey(FormField, null = True)
    group_field = models.ForeignKey(FieldGroup, null = True)

       
    def __unicode__(self):
        if self.form_field:
            return u'%s - %s' %(self.form_field.label, self.name)
        else:
            return u'%s - %s' %(self.group_field.label, self.name)



class Instance(models.Model):
    """Class: `Instance`. 
       :param creation_date: Instance creation date.
       :param modification_date: Instance modification date.
       :param signature: User signature.
       :param form: form of this instance.
       :attention Signature may be replaced by username
       :todo Signature"""
    creation_date = models.DateField()
    modification_date = models.DateField()
    signature = models.CharField(max_length = 128)
    form = models.ForeignKey(Form)
    editable = models.BooleanField()
    longitude = models.FloatField(null=True)
    latitude = models.FloatField(null=True)

    def __unicode__(self):
        return u'%s - %s' %(self.form.name, self.id)



class InstanceField(models.Model):
    """Class: `InstanceField`. 
       :param instance: Instance which this field (value) is related to.
       :param instance_order: Order of this field in the instance.
       :param form_fields: form-field related with this instance-field.
       :attention instance_order is different of field_order because
                  of the dynamic fields"""
    instance = models.ForeignKey(Instance)
    instance_order = models.SmallIntegerField()
    form_field = models.ForeignKey(FormField)


    real_type = models.ForeignKey(ContentType, editable=False, null=True)

    def save(self, *args, **kwargs):
        if not self.id:
            self.real_type = self._get_real_type()
        super(InstanceField, self).save(*args, **kwargs)

    def _get_real_type(self):
        return ContentType.objects.get_for_model(type(self))

    def cast(self):
        return self.real_type.get_object_for_this_type(pk=self.pk)

    class Meta:
        unique_together = ('instance', 'instance_order')
        ordering = ['instance']
        #abstract = True

    def __unicode__(self):
        return u'%s - %s' %(self.form_field.label, self.id)




class TextField(InstanceField):
    """Class: `TextField`. 
       :param value: The text itself."""
    value = models.CharField(max_length = 128)

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)
    
    
class TextAreaField(InstanceField):
    """Class: `TextAreaField`. 
       :param value: The text itself."""
    value = models.CharField(max_length = 1024)

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)


class NumericField(InstanceField):
    """Class: `TextField`. 
       :param value: The text itself."""
    value = models.IntegerField()

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)


class DateField(InstanceField):
    """Class: `DateField`. 
       :param day_value: The Day.
       :param month_value: The Month.
       :param year_value: The Year."""
    value = models.DateField()

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)


class RadioField(InstanceField):
    """Class: `RadioField`. 
       :param value: The id of the selected option."""
    value = models.CharField(max_length = 16) # TIPO DE DATOS ID DE DJANGO

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)
    
    
class CheckField(InstanceField):
    """Class: `CheckField`. 
       :param value: True or False (if TRIESTATE property also None)."""
    value = models.CharField(max_length = 5) # TIPO DE DATOS ID DE DJANGO

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)
    
    
class ComboField(InstanceField):
    """Class: `ComboField`. 
       :param value: The id of the selected option."""
    value = models.CharField(max_length = 5) # TIPO DE DATOS ID DE DJANGO

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)



class ImageField(InstanceField):
    """Class: `ImageField`. 
       :param value: The image itself.
       :todo ImageField parameters"""
    value = models.ImageField(upload_to = 'beehive/images')

    def __unicode__(self):
        return unicode(self.value)
    
    def get_text(self):
        return unicode(self.value)



# ---------------------------------------------------------
# TextAreaField
# ---------------------------------------------------------
#class TextAreaField(TextField):
    #value = models.TextField()
