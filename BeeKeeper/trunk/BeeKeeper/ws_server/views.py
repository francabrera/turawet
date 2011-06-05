"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""


from django.views.decorators.csrf import csrf_exempt
from soaplib.service import soapmethod
from soaplib.serializers.primitive import Array, Integer, String
#from soaplib.serializers.binary import Attachment
from soaplib_handler import DjangoSoapService
#from os.path import exists
from dummy import Dummy, DummyWs

from BeeKeeper.db_models.models import Form
from models_ws import WsFormPreview, WsXmlForm, WsUploadStatus

from BeeKeeper.form_xml2db_parser.form_xml2db_parser import FormXmldbParser
from BeeKeeper.instance_xml2db_parser.instance_xml2db_parser import InstanceXmldbParser 


class SoapService(DjangoSoapService):

    ##TODO La IP deberia ser calculada y que no este a mano.
    __tns__ = 'http://193.145.110.236/ws_server/'


    """
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

    """

    @soapmethod(_returns = Array(WsFormPreview))
    def get_all_forms_preview(self):
        ''' 
            Sending the preview of the forms in the DB
        '''
        forms = Form.objects.values('name', 'version')

        wsforms = []
        for form in forms:
            wsforms.append(WsFormPreview(form))
        return wsforms

    @soapmethod(String, Integer, _returns = WsXmlForm)
    def get_xmlform_by_name_version(self, name, version):
        '''
            Sending the XML of a selected form by name-version
        '''
        form = Form.objects.get(name = name, version = version)
        ws_xml_form = WsXmlForm(form)
        return ws_xml_form


    @soapmethod(Array(Integer), _returns = Array(WsXmlForm))
    def get_forms_by_ids(self, forms_id):
        '''
            Sending the XML of a selected form by ID
        '''
        forms = []
        for form_id in forms_id:
            form = Form.objects.get(id = form_id)
            list.append(WsXmlForm(form))
        return forms


    @soapmethod(String, _returns = WsUploadStatus)
    def upload_new_form(self, xml):
        '''
            Receiving an XML (form) and calling the parser
        '''

        parser = FormXmldbParser()
        is_inserted = parser.generateModels(xml)
        
        return is_inserted
    

    @soapmethod(String, _returns = WsUploadStatus)
    def upload_new_instance(self, xml):
        '''
            Receiving an XML (instance) and calling the parser
        '''

        parser = InstanceXmldbParser()
        is_inserted = parser.generateModels(xml)
        
        return is_inserted
    

service = csrf_exempt(SoapService())
