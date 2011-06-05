'''
Created on 05/06/2011

@author: bee
'''
import logging
from suds.client import Client

logging.basicConfig(level=logging.INFO)
logging.getLogger('suds.client').setLevel(logging.DEBUG)

class WsClient(Client):
   
    def __init__(self):
        url = 'http://193.145.110.236/ws_server/service.wsdl'
        Client.__init__(self, url, cache=None)