# Create your views here.
from django.shortcuts import render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext
from django.conf import settings
from BeeKeeper.beekeeper.post_form import NewForm


import io

#Statistics
import sys
import cairo
import pycha.pie

# Imports
from BeeKeeper.db_models.models import Form, Section, FormField, Instance,\
    InstanceField, Property, ImageField


def showIndex (request):
    
    forms = Form.objects.all()
    
    return render_to_response('index.html');


def showFormList (request):
    
    ##
    ## if request.user.is_autenticated
    ##
    
    context = RequestContext(request)
    # We obtain the forms
    context["forms"] = Form.objects.all()
    # If post
    if request.method == 'POST':
        postform = NewForm(request.POST)
        if postform.is_valid() :
            context["formToDelete"] = postform.cleaned_data['elementToDelete']
        context["deleting"] = True
    else:
        context["deleting"] = False
    
    return render_to_response('formularios.html', context);


def showForm (request, formid):

    context = RequestContext(request)
    # We obtain the form
    form = Form.objects.filter(id = formid)
    context["form"] = form[0]
    # If post
    if request.method == 'POST':
        postform = NewForm(request.POST)
        if postform.is_valid() :
            context["formToDelete"] = postform.cleaned_data['elementToDelete']
        context["deleting"] = True
    else:
        context["deleting"] = False
    
    return render_to_response('ver_formulario.html', context);


def showFormStatistics (request, formid):
    
    form = Form.objects.filter(id = formid)
    form = form[0]
    
    return render_to_response('ver_estadisticas_formulario.html', {'form': form})


def createFormFieldStatistics (request, formid, formfieldid):
    
    #The current form
    form = Form.objects.filter(id = formid)
    form = form[0]
    # The form field for the statistic
    formfield = FormField.objects.filter(id=formfieldid)
    formfield = formfield[0]
    
    if ((formfield.type == "COMBO") or (formfield.type == "RADIO")):
        # All the instancefields related to the formfield
        instancefields = InstanceField.objects.filter(form_field=formfield)
        
        # Forming the dataset for the chart
        # Loading the values form the instancefields in the database where
        # form_field is the one selected
        dataset = []
        for field in instancefields:
            i = 0
            for tempset in dataset:
                if tempset[0] == field.get_text():
                    tempset[1][0][1] = tempset[1][0][1] + 1
                else:
                    i = i + 1
            if i == len(dataset):
                tempset = [field.get_text(), [[0, 1]]]
                dataset.append(tuple(tempset))
        dataset = tuple(dataset)
            
    
        # Generating the statistic graph
        surface = cairo.ImageSurface(cairo.FORMAT_ARGB32, 450, 450)
    

        options = {
            'axis': {
                'x': {
                    #'ticks': [dict(v=i, label=d[0]) for i, d in enumerate(lines)],
                }
            },
            'legend': {
                'hide': True,
            },
            'background': {
                'hide': True,
            },
            #'title': 'Pie Chart',
            #'pieRadius': 0.8,
            #'padding': {'left': 20, 'right': 20, 'top': 20, 'bottom': 20}
        }
        chart = pycha.pie.PieChart(surface, options)
    
        chart.addDataset(dataset)
        chart.render()
    
        #filename = 'piechart.png'
        #output = settings.IMAGES_BEEKEEPER_ROOT+'/'+filename
        
    output = io.BytesIO()
    surface.write_to_png(output)

  
    return HttpResponse(output.getvalue(), mimetype="image/png")
             

def deleteForm (request, formid):
    
    form = Form.objects.filter(id = formid)
    form = form[0]
    if form:
        form.delete()
    # Once deleted we show the other forms
    return showFormList(request)


def showInstanceList (request, formid):
    context = RequestContext(request)
    # We obtain the insance
    form = Form.objects.filter(id = formid)
    form = form[0]
    if form:
        context["instances"] = Instance.objects.filter(form=form);
    # If post
    if request.method == 'POST':
        postform = NewForm(request.POST)
        if postform.is_valid() :
            context["instanceToDelete"] = postform.cleaned_data['elementToDelete']
        context["deleting"] = True
    else:
        context["deleting"] = False
    
    
    return render_to_response('instancias.html', context)


def showInstancesMap (request, formid):
    
    form = Form.objects.filter(id = formid)
    form = form[0]
    instances_images = []
    if form:
        instances = Instance.objects.filter(form=form);
        for instance in instances:
            auximage = ImageField.objects.filter(instance=instance)
            if auximage:
                auximage = auximage[0]
                instances_images.append((instance, auximage))
            else:
                instances_images.append((instance, None))
    
    return render_to_response('mapa_instancias.html', {'form': form, 'instances': instances_images})


def showInstance (request, instanceid):
    context = RequestContext(request)
    # We obtain the insance
    instance = Instance.objects.filter(id = instanceid)
    context["instance"] = instance[0]
    if instance:
        context["instance_fields"] = InstanceField.objects.filter(instance=instance[0]).all()
    else:
        context["instance_fields"] = None
    # If post
    if request.method == 'POST':
        postform = NewForm(request.POST)
        if postform.is_valid() :
            context["instanceToDelete"] = postform.cleaned_data['elementToDelete']
        context["deleting"] = True
    else:
        context["deleting"] = False
    
    
    return render_to_response('ver_instancia.html', context)

    
#    instance = Instance.objects.filter(id = instanceid)
#    instance = instance[0]
#    if instance:
#        instance_fields = InstanceField.objects.filter(instance=instance).all()
#    else:
#        instance_fields = None
    
#    return render_to_response('ver_instancia.html', {'instance': instance, 'instance_fields': instance_fields });


def showInstanceMap (request, instanceid):
    
    instance = Instance.objects.filter(id = instanceid)
    instance = instance[0]   
    auximage = ImageField.objects.filter(instance=instance)
    if auximage:
        auximage = auximage[0]

     
    return render_to_response('mapa_instancias.html', {'form': None, 'instances': [(instance,auximage)]})


def deleteInstance (request, instanceid):
    instance = Instance.objects.filter(id = instanceid)
    instance = instance[0]
    if instance:
        instance.delete()
    # Once deleted we show the other forms
    return showInstanceList(request, instance.form.id)