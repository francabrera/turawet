"""
    Tests unitarios para el parser y los Modelos de la BBDD
"""
#from django.test import TestCase
from django.test import TestCase
#import logging
    #from suds.xsd.doctor import Import, ImportDoctor

#from suds.client import Client
from BeeKeeper.db_models.models import *
from BeeKeeper.form_xml2db_parser.form_xml2db_parser import FormXmldbParser 

import os.path

class FormXmldbParserTest(TestCase):

    def setUp(self):
        # Creationg a parser
        self.parser = FormXmldbParser()
        # XML Sample
        xmlpath = os.path.dirname(__file__) + '/resources/formulario.xml'
        f = open(xmlpath, "r")
        xml = f.read()
        f.close()
        #print(xml)
        self.parser.generateModels(xml)


    def test_num_forms(self):
        #a = ['larry', 'jose']
        #self.assertEqual(my_func(a, 0), 'larry')

        # DB objects
        forms = Form.objects.all()
        
        # TEST 1: Num formularios
        self.assertEqual(len(forms), 1)


    def test_num_section(self):
        sections = Section.objects.all()       
        # TEST 2: Num secciones
        self.assertEqual(len(sections), 1)


    def test_num_fields(self):
        form_fields = FormField.objects.all()
        #TO-DO:
        # TEST 3: N campos -> Now it must fail
        # There are 103 fields in the form, but 5 are still strange types
        self.assertEqual(len(form_fields), 13)
        

    def test_first_field_label(self):
        # Now we extract the first field
        form_field = FormField.objects.get(pk=1)
        # Must be labeled 'Etiqueta1'
        self.assertEqual(form_field.label, 'Etiqueta1')

        
    def test_num_groups(self):        
        groups = FieldGroup.objects.all()
        self.assertEqual(len(groups), 2)

    
    def test_image_gallery_field_exists(self):
        #TO-DO:
        # Must fail because IMAGE types are still not implemented in the parser
        image_gallery_field = FormField.objects.filter(label=u'Im\u00E1genes')
        self.assertEqual(len(image_gallery_field), 1)


    def test_num_properties(self):        
        #TO-DO:
        # Must fail because LOCATION type is still not implemented in the parser
        properties = Property.objects.all()
        self.assertEqual(len(properties), 9)        

        
    def test_group_property(self):
        max_file_size = Property.objects.filter(name='max_file_size')
        # In our initial_data sample there is only one property with the name 'max_file_size'
        if len(max_file_size) == 1:
            self.assertEqual(max_file_size[0].value, u'128')


    def test_field_property(self):
        font = Property.objects.filter(name='font')
        # In our initial_data sample there is only one property with the name 'font'
        if len(font) == 1:
            self.assertEqual(font[0].value, u'Arial')


    def test_radio_options_null_field(self):
        #One sample option
        field_options = FieldOption.objects.all()
        # The Radio1 field has 2 options
        self.assertNotEqual(field_options[0].form_field, None)
            

    def test_num_radio_options(self):
        form_fields = FormField.objects.filter(label=u'Radio1')
        
        if len(form_fields) == 1:
            #There is only one instance field of this Form_field
            field_options = FieldOption.objects.filter(form_field=form_fields[0])
            # The Radio1 field has 2 options
            self.assertEqual(len(field_options), 2)
            
    def test_num_options(self):
        field_options = FieldOption.objects.all()
        # The Radio1 field has 2 options
        self.assertEqual(len(field_options), 4)