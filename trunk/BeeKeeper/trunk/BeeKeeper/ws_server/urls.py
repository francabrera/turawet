'''
Created on 08/02/2011

@author: nicopernas
'''
from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('ws_server.views',
    (r'service.wsdl', 'service'),
    (r'service', 'service'),
)
