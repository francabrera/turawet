# Create your views here.
from django.shortcuts import render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext

#from numpy import zeros

# Imports
from BeeKeeper.db_models.models import Form, Section, FormField, Instance,\
    InstanceField


def showFormList (request):
    
    forms = Form.objects.all();
    
    return render_to_response('formularios.html', {'forms': forms });


def showForm (request, formid):
    
    form = Form.objects.filter(id = formid);
    if form:
        sections = Section.objects.filter(form = form)
        i = 0
        #form_fields = zeros(len(sections))
        #for section in sections:
        #    form_fields[i] = FormField.objects.filter(section=section)
        #    i += 1
        form_fields = FormField.objects.filter(form=form, section=sections[0])
    else:
        sections = None
        form_fields = None
    
    
    return render_to_response('ver_formulario.html', {'form': form, 'sections': sections, 'form_fields': form_fields });


def showInstanceList (request, formid):
    
    form = Form.objects.filter(id = formid);
    if form:
        instances = Instance.objects.filter(form=form);
    
    return render_to_response('instancias.html', {'instances': instances });


def showInstance (request, instanceid):
    
    instance = Instance.objects.filter(id = instanceid);
    if instance:
        sections = Section.objects.filter(form = instance.form)
        i = 0
        #form_fields = zeros(len(sections))
        #for section in sections:
        #    form_fields[i] = FormField.objects.filter(section=section)
        #    i += 1
        instance_fields = InstanceField.objects.filter(instance=instance, section=sections[0])
    else:
        sections = None
        instance_fields = None
    
    
    return render_to_response('ver_instancia.html', {'instance': instance, 'sections': sections, 'instance_fields': instance_fields });