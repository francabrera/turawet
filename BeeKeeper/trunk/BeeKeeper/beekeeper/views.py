# Create your views here.
from django.shortcuts import render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext

#from numpy import zeros

# Imports
from BeeKeeper.db_models.models import Form, Section, FormField


def showFormList (request):
    
    forms = Form.objects.all();
    
    return render_to_response('formularios.html', {'forms': forms });


def showForm (request, formid):
    
    form = Form.objects.get(id = formid);
    sections = Section.objects.filter(form = form)
    i = 0
    #form_fields = zeros(len(sections))
    #for section in sections:
    #    form_fields[i] = FormField.objects.filter(section=section)
    #    i += 1
    form_fields = FormField.objects.filter(section=sections[0])
    
    
    return render_to_response('ver_formulario.html', {'form': form, 'sections': sections, 'form_fields': form_fields });