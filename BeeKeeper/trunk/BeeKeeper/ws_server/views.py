'''
Created on 08/02/2011

@author: nicopernas
'''


from django.views.decorators.csrf import csrf_exempt
from soaplib.service import soapmethod
from soaplib.serializers.primitive import Array, Integer, String
from soaplib.serializers.binary import Attachment
from soaplib_handler import DjangoSoapService
from os.path import exists
from dummy import Dummy, DummyWs

from BeeKeeper.db_models.models import Form
from models_ws import WsFormPreview, WsXmlForm


class SoapService(DjangoSoapService):

    __tns__ = 'http://localhost:8000/ws_server/'

    @soapmethod(_returns = DummyWs)
    def get_dummy(self):

        dummy = Dummy("Soy un objeto tonto", 123)
        dw = DummyWs(dummy)
        return dw

    @soapmethod(_returns = Array(DummyWs))
    def get_all_dummies(self):

        list = []
        for i in range(3):
            list.append(DummyWs(Dummy("Soy el objeto tonto numero " + str(i))))
        return list

    @soapmethod(_returns = Attachment)
    def get_file(self):

        file_path = "/Users/nicopernas/Downloads/Firma.gif"
        if not exists(file_path):
            raise Exception("File [%s] not found" % file_path)

        document = Attachment(fileName = file_path)
        return document


    @soapmethod(_returns = Array(WsFormPreview))
    def get_all_forms_preview(self):
        ''' 
        :param name: section name. 
        :param order: section order inside a form.
        :param form: references the form in which this section is.
        '''
        forms = Form.objects.values('name', 'version')

        wsforms = []
        for form in forms:
            wsforms.append(WsFormPreview(form))
        return wsforms


    @soapmethod(String, Integer, _returns = WsXmlForm)
    def get_xmlform_by_name_version(self, name, version):
        form = Form.objects.get(name = name, version = version)
        ws_xml_form = WsXmlForm(form)
        return ws_xml_form

    '''
    @soapmethod(Array(Integer), _returns=Array(WsForm))
    def get_forms_by_ids(self, forms_id):
        list = []
        for form_id in forms_id:
            form = Form.objects.get
            list.append(WsForm(form))
        return None
    '''
    '''
    def upload_new_form(self, form):
    
        return
    
    def upload_new_instance(self):
        return
    '''
service = csrf_exempt(SoapService())
