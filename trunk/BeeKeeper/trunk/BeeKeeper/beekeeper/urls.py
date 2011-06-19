
from django.conf.urls.defaults import *
from django.conf import settings

# Uncomment the next two lines to enable the admin:


urlpatterns = patterns('',
    (r'css/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.CSS_BEEKEEPER_ROOT, 'show_indexes': False}),
    (r'images/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.IMAGES_BEEKEEPER_ROOT, 'show_indexes': False}),
    (r'js/(?P<path>.*)$', 'django.views.static.serve', {'document_root': settings.JS_BEEKEEPER_ROOT, 'show_indexes': False}),
)

urlpatterns += patterns('beekeeper.views',
    (r'', 'showIndex'),
    (r'show_form_list$', 'showFormList'),
    (r'show_form/(?P<formid>\d+)/$', 'showForm'),
    (r'show_form_statistics/(?P<formid>\d+)/$', 'showFormStatistics'),
    (r'create_form_field_statistics/(?P<formid>\d+)/(?P<formfieldid>\d+)/$', 'createFormFieldStatistics'),
    (r'delete_form/(?P<formid>\d+)/$', 'deleteForm'),
    (r'show_instance_list/(?P<formid>\d+)/$', 'showInstanceList'),
    (r'show_instances_map/(?P<formid>\d+)/$', 'showInstancesMap'),
    (r'show_instance/(?P<instanceid>\d+)/$', 'showInstance'),
    (r'show_instance_map/(?P<instanceid>\d+)/$', 'showInstanceMap'),
    (r'delete_instance/(?P<instanceid>\d+)/$', 'deleteInstance'),
)
