"""WebServices Tests
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

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

    def test_get_all_forms_preview(self):
        '''
            Test if all forms preview obtained by the ws call
            are the same that are obtained by the Form.objects.all() call
        '''
        forms_ws = self.service.get_all_forms_preview()
        forms = Form.objects.all()
        self.failUnlessEqual(len(forms), len(forms_ws), 'Number of forms obtained...')

        for i in range(len(forms)):
            self.failUnlessEqual(forms[i].name, forms_ws[i].name, 'Same names...')
            self.failUnlessEqual(forms[i].version, forms_ws[i].version, 'Same versions...')


    def test_get_xmlform_by_name_version(self):
        '''
          Test that both: "Form.objects.get(id = 1)" and  "get_xmlform_by_name_version"
        '''
        form = Form.objects.get(id = 1)
        forms_ws = self.service.get_xmlform_by_name_version(name = form.name, version = form.version)
        self.failUnlessEqual(form.xml, forms_ws.xml, 'Same XML...')

    def test_get_forms_by_ids(self):
        '''
        '''
