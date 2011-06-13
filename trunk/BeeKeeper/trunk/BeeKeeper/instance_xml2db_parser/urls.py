from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('instance_xml2db_parser.views',
    (r'instance_xml2db_parser', 'try_instance_xml2db_parser'),
)
