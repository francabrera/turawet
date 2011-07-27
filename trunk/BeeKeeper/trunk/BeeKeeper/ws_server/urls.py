'''
Created on 08/02/2011

@author: nicopernas
'''
from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
#from django.contrib import admin
#admin.autodiscover()

urlpatterns = patterns('',
    (r'^service.wsdl$', 'BeeKeeper.ws_server.views.service'),
    (r'^service$', 'BeeKeeper.ws_server.views.service'),
)
