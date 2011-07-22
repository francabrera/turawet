from django.conf.urls.defaults import *



from django.contrib import admin
admin.autodiscover()


urlpatterns = patterns('',
    # Example:
    #(r'^BeeKeeper/', include('BeeKeeper.foo.urls')),
    
    (r'^db_models/', include('BeeKeeper.db_models.urls')),
    
    (r'^form_xml2db_parser/', include('BeeKeeper.form_xml2db_parser.urls')),

    (r'^instance_xml2db_parser/', include('BeeKeeper.instance_xml2db_parser.urls')),
    
    #(r'^beekeeper/', include('beekeeper.urls')),
    
    (r'^ws_server/', include('BeeKeeper.ws_server.urls')),
    # Uncomment the admin/doc line below to enable admin documentation:
    (r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    (r'^admin/', include(admin.site.urls)),
    
    (r'^$', include('BeeKeeper.beekeeper.urls')),
)
