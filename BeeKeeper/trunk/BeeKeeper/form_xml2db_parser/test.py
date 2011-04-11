"""
    Tests unitarios para los Modelos de la BBDD
"""
#from django.test import TestCase
from django.test import TestCase
#import logging
    #from suds.xsd.doctor import Import, ImportDoctor

#from suds.client import Client
from BeeKeeper.db_models.models import Form
from BeeKeeper.ws_server import views

class WsTest(TestCase):
    fixtures = ['formularios.json']

    def setUp(self):
        #logging.basicConfig(level = logging.INFO)
        #logging.getLogger('suds.client').setLevel(logging.INFO)

        #self.url = 'http://localhost:8000/ws_server/service.wsdl'
        #self.client = Client(self.url, cache = None)
        self.service = views.SoapService()