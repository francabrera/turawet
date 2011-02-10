'''
Created on 08/02/2011

@author: nicopernas
'''


from django.views.decorators.csrf import csrf_exempt
from soaplib.service import soapmethod
from soaplib.serializers.primitive import Array
from soaplib.serializers.binary import Attachment
from soaplib_handler import DjangoSoapService
from os.path import exists
from dummy import Dummy

class SoapService(DjangoSoapService):

    __tns__ = 'http://localhost:8000/'
    
    @soapmethod(_returns=Dummy)
    def get_dummy(self):
        
        dummy = Dummy("Soy un objeto tonto")
        return dummy
    
    @soapmethod(_returns=Array(Dummy))
    def get_all_dummies(self):
        
        list = []
        for i in range(3):
            list.append(Dummy("Soy el objeto tonto numero " + str(i)))
        return list
    
    @soapmethod(_returns=Attachment)
    def get_file(self):
        
        file_path = "/Users/nicopernas/Downloads/Firma.gif"
        if not exists(file_path):
            raise Exception("File [%s] not found"%file_path)
    
        document = Attachment(fileName=file_path)
        return document
    
    '''
    
    '''
    @soapmethod(_returns=Array)
    def get_all_forms_preview(self):
        
        forms = [] #  
        return forms

    '''
    
    '''
    def get_form_by_id(self, form_id):
        
        return
       
    '''
    
    '''       
    def upload_new_form(self, form):
        
        return
    
    '''
    
    '''
    def upload_new_instance(self):
    
        return
    
    
service = csrf_exempt(SoapService())
