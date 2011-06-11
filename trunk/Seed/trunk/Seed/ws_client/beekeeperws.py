'''
*BeeKeeper Services Client*
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.1
'''
import logging
from suds.client import Client

logging.basicConfig(level=logging.INFO)
logging.getLogger('suds.client').setLevel(logging.DEBUG)


def upload_new_form(formXML):
    url = 'http://193.145.110.236/ws_server/service.wsdl'
    myclient = Client(url, cache=None)
    return myclient.service.upload_new_form(formXML)
