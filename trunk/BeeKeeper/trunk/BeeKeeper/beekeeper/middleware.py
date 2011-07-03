from django.contrib.auth.views import login
from django.http import HttpResponseRedirect
import re

class SiteLogin:
    "This middleware requires a login for every view"
    def process_request(self, request):
        if re.search('beekeeper', request.path) and (not re.search('images', request.path)) and \
           (not re.search('css', request.path)) and (not re.search('js', request.path)) and \
           request.path != '/beekeeper/accounts/login/' and request.user.is_anonymous():
            if request.POST:
                return login(request)
            else:
                return HttpResponseRedirect('/beekeeper/accounts/login/?next=%s' % request.path)
