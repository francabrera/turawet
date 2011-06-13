# Create your views here.
from django.shortcuts import render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext

#from numpy import zeros

# Imports
from BeeKeeper.db_models.models import Form, Section, FormField, Instance,\
    InstanceField, Property


def showFormList (request):
    
    forms = Form.objects.all()
    
    return render_to_response('formularios.html', {'forms': forms });


def showForm (request, formid):
    
    form = Form.objects.filter(id = formid)
    form = form[0]

    
    return render_to_response('ver_formulario.html', {'form': form})


def showInstanceList (request, formid):
    
    form = Form.objects.filter(id = formid)
    form = form[0]
    if form:
        instances = Instance.objects.filter(form=form);
    
    return render_to_response('instancias.html', {'instances': instances })


def showInstance (request, instanceid):
    instance = Instance.objects.filter(id = instanceid)
    instance = instance[0]
    if instance:
        instance_fields = InstanceField.objects.filter(instance=instance).all()
    else:
        instance_fields = None
    
    
    return render_to_response('ver_instancia.html', {'instance': instance, 'instance_fields': instance_fields });