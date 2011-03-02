from django.conf.urls.defaults import *
from django.conf import settings

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()


urlpatterns = patterns('',
    (r'^css/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.CSS_MODELER_ROOT, 'show_indexes': False}),
    (r'^images/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.IMAGES_MODELER_ROOT, 'show_indexes': False}),
    (r'^js/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.JS_MODELER_ROOT, 'show_indexes': False}),
)

urlpatterns += patterns('modeler.views',
    (r'^$', 'showModeler'),
)
