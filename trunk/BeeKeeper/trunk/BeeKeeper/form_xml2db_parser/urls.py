from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('form_xml2db_parser.views',
    (r'form_xml2db_parser', 'try_form_xml2db_parser'),
)
