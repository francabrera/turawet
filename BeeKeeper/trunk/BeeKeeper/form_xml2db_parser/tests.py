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

    def xml_2_db(self):
        #a = ['larry', 'jose']
        #self.assertEqual(my_func(a, 0), 'larry')
        
        # XML Sample
        xml = os.path.dirname(__file__) + 'resources/formulario.xml'
        print(xml)
        # Creationg a parser
        self.parser = FormXmldbParser()
        self.parser.generateModels(xml)
        # DB objects
        forms = Form.objects.all()
        sections = Section.objects.all()
        formfields = FormField.objects.all()
        # La base de datos que se levanta para Test es de mentira. Sólo tiene lo que hemos insertado ahora

        # TEST 1: Nº formularios
        self.assertEqual(len(forms), 1)

        # TEST 2: Nº secciones
        self.assertEqual(len(sections), 1)

        # TEST 3: Nº campos
        self.assertEqual(len(formfields), 10)
