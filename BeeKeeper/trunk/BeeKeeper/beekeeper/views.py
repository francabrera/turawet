# Create your views here.
from django.shortcuts import render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext

# Imports
from BeeKeeper.db_models.models import Form


def showForms (request):
    
    forms = Form.objects.all();
    
    return render_to_response('formularios.html', {'forms': forms });
