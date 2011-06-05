"""
    Tests unitarios para el parser y los Modelos de la BBDD
"""
#from django.test import TestCase
from django.test import TestCase
#import logging
    #from suds.xsd.doctor import Import, ImportDoctor

#from suds.client import Client
from BeeKeeper.db_models.models import *
from BeeKeeper.instance_xml2db_parser.instance_xml2db_parser import InstanceXmldbParser 

import os.path

class InstanceXmldbParserTest(TestCase):

    fixtures = ['test_form_v1.json']

    def setUp(self):
        # Creationg a parser
        self.parser = InstanceXmldbParser()
        # XML Sample
        xmlpath = os.path.dirname(__file__) + '/resources/instancia.xml'
        f = open(xmlpath, "r")
        xml = f.read()
        f.close()
        #print(xml)
        self.parser.generateModels(xml)


    def test_number_of_instances(self):
        #a = ['larry', 'jose']
        #self.assertEqual(my_func(a, 0), 'larry')

        # DB objects
        instances = Instance.objects.all()
        
        # TEST 1: Num formularios
        self.assertEqual(len(instances), 1)


    def test_number_of_fields(self):
        # DB objects
        instance_fields = InstanceField.objects.all()
        
        # TEST 1: Num formularios
        self.assertEqual(len(instance_fields), 15)


    def test_text_field_value(self):
        # DB objects
        instance_field = TextField.objects.get(pk=1)
        
        # TEST 1: Num formularios
        self.assertEqual(instance_field.value, 'Texto escrito')     
        

    def test_text_field_instantiation(self):
        instance_model = Instance.objects.get(pk=1)
        field_model = FormField.objects.get(pk=1)
        instance_field_model = TextField(value='hola')
        instance_field_model.form_field = field_model
        instance_field_model.instance = instance_model
        instance_field_model.instance_order = 9
        instance_field_model.save()
        
        prueba = TextField.objects.get(value='hola')
        
        self.assertEqual(prueba.instance_order, 9)
        
        
    def test_fields_inheritance(self):
        #### NOT INSTANCE, Just FORMFIELD
        form_fields = FormField.objects.filter(label=u'Radio1')
        
        if len(form_fields) == 1:
            #There is only one instance field of this Form_field
            radio_fields = InstanceField.objects.filter(form_field=form_fields[0])
            # WARNING: The parent has to know his child (Radio is a child of FieldForm)
            self.assertEqual(len(radio_fields), 1)


    def test_radio_options(self):
        form_fields = FormField.objects.filter(label=u'Radio1')
        
        if len(form_fields) == 1:
            #There is only one instance field of this Form_field
            radio_fields = RadioField.objects.get(form_field=form_fields[0])
            field_options = FieldOption.objects.filter(form_field=radio_fields)
            # The Radio1 field has 2 options
            self.assertEqual(len(field_options), 2)