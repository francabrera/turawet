'''
Created on 04/02/2011

@author: nicopernas
'''
from soaplib.wsgi_soap import SimpleWSGISoapApp 
from django.http import HttpResponse
import StringIO

class DumbStringIO(StringIO.StringIO): 
    def read(self, n): 
        return self.getvalue().encode()

class DjangoSoapService(SimpleWSGISoapApp):
    
    def __call__(self, request):
        django_response = HttpResponse()
        def start_response(status, headers):
            status, reason = status.split(' ', 1)
            django_response.status_code = int(status)
            for header, value in headers:
                django_response[header] = value
    
        environ = request.META.copy()
        body = ''.join(['%s=%s' % v for v in request.POST.items()])
        environ['CONTENT_LENGTH'] = len(body)
        environ['wsgi.input'] = DumbStringIO(body)
        environ['wsgi.multithread'] = False
    
        response = super(DjangoSoapService, self).__call__(environ, start_response)

        django_response.content = "\n".join(response)

        return django_response
