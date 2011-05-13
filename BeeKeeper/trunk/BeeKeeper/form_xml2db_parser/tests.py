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


    def test_xml_2_db(self):
        #a = ['larry', 'jose']
        #self.assertEqual(my_func(a, 0), 'larry')

        # DB objects
        forms = Form.objects.all()
        sections = Section.objects.all()
        formfields = FormField.objects.all()        
        
        # TEST 1: Num formularios
        self.assertEqual(len(forms), 1)

        # TEST 2: Num secciones
        self.assertEqual(len(sections), 1)

        # TEST 3: N campos -> Now it must fail
        self.assertEqual(len(formfields), 10)
