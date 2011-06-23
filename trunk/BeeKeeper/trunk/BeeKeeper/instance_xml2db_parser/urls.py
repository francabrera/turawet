from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('instance_xml2db_parser.views',
    (r'big', 'instance_xml2db_parser_xml_big'),
    (r'p1', 'instance_xml2db_parser_xml_p1'),
)
