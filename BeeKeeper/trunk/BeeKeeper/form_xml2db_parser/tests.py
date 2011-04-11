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
        # Creationg a parser
        self.parser = FormXmldbParser()
        self.parser.generateModels(xml)
        # All the forms in the DB
        forms = Form.objects.all()
        # La base de datos que se levanta para Test es de mentira

