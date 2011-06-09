'''
Created on 05/06/2011

@author: bee
'''
import logging
from suds.client import Client

logging.basicConfig(level=logging.INFO)
logging.getLogger('suds.client').setLevel(logging.DEBUG)


def upload_new_form(formXML):
    url = 'http://193.145.110.236/ws_server/service.wsdl'
    myclient = Client(url, cache=None)
    return myclient.service.upload_new_form(formXML)
