"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

# Imports
from django.db import models

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
    xml = models.XMLField(schema_path = 'NO OLVIDAR')

    class Meta:
        unique_together = ('name', 'version')
        ordering = ['name']

    def __unicode__(self):
        return self.name



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

    def __unicode__(self):
        return self.name



class FieldGroup(models.Model):
    """Class: `FieldGroup`. 
       :param label: Field-group label in the form."""
    label = models.CharField(max_length = 256)

    def __unicode__(self):
        return self.label



class FieldList(models.Model):
    """Class: `FieldList`. 
       :param label: Field-list label in the form.
       :param group: group associated with the list.
       :attention Group relation is in this class due to
                  the group could exist without being in
                  a list. But a list is always related to groups"""
    label = models.CharField(max_length = 256)
    group = models.OneToOneField(FieldGroup)

    def __unicode__(self):
        return self.label



class FormField(models.Model):
    """Class: `FieldList`. 
       :param label: Field label in the form.
       :param section_order: The order of the field in the current section.
       :param section: The current section.
       :param field_group: A Field could belong to a group.
       :param field_group_order: The order of the field in the group (if any).
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

    class Meta:
        unique_together = ('label', 'section', 'section_order')

    def __unicode__(self):
        return self.label



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

    def __unicode__(self):
        return self.form.name + " - " + self.id



class InstanceField(models.Model):
    """Class: `InstanceField`. 
       :param instance: Instance which this field (value) is related to.
       :param instance_order: Order of this field in the instance.
       :param form_fields: form-field related with this instance-field.
       :attention instance_order is different of field_order because
                  of the dynamic fields"""
    instance = models.ForeignKey(Instance)
    instance_order = models.SmallIntegerField()
    form_fields = models.ForeignKey(FormField)

    class Meta:
        unique_together = ('instance', 'instance_order')

    def __unicode__(self):
        return self.form_fields.label



class ImageField(InstanceField):
    """Class: `ImageField`. 
       :param value: The image itself.
       :todo ImageField parameters"""
    value = models.ImageField(upload_to = '/')

    def getImage(self):
        return self.value



class TextField(InstanceField):
    """Class: `TextField`. 
       :param value: The text itself."""
    value = models.CharField(max_length = 128)

    def getText(self):
        return self.value



# ---------------------------------------------------------
# TextAreaField
# ---------------------------------------------------------
#class TextAreaField(TextField):
    #value = models.TextField()
