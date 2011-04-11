from django.conf.urls.defaults import *


from django.contrib import admin
admin.autodiscover()


urlpatterns = patterns('',
    # Example:
    #(r'^BeeKeeper/', include('BeeKeeper.foo.urls')),
    (r'^db_models/', include('db_models.urls')),
    
    (r'^ws_server/', include('ws_server.urls')),
    # Uncomment the admin/doc line below to enable admin documentation:
    (r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    (r'^admin/', include(admin.site.urls)),
)
