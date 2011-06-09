
from django.conf.urls.defaults import *
from django.conf import settings

# Uncomment the next two lines to enable the admin:


urlpatterns = patterns('',
    (r'^css/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.CSS_MODELER_ROOT, 'show_indexes': False}),
    (r'^images/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.IMAGES_MODELER_ROOT, 'show_indexes': False}),
)

urlpatterns += patterns('beekeeper.views',
    (r'^$', 'showForms'),
    
)
