"""
Views for Seed web modeler
Project: Turawet
Date: 01-03-2011
"""
from django.http import HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext
from Seed.modeler.forms import NewForm

from xml.etree.ElementTree import ElementTree, tostring, XML

def showModeler(request):
    context = RequestContext(request)
    if request.method == 'POST':
        form = NewForm(request.POST)
        if form.is_valid() :
            context['prueba'] = form.cleaned_data['fieldList']
            context['prueba2'] = parseSentFormXML
#        if form.is_valid() :
#            return HttpResponseRedirect('/formList')
    else:
        form = NewForm()
        
    context['form'] = form
    return render_to_response('create.html', context)

def parseSentFormXML (xml):
    if xml is None:
        return None
    else:
        formXML = XML(xml)
        sections = formXML.findall("section")
        return tostring(sections);


