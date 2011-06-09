"""
Views for Seed web modeler
Project: Turawet
Date: 01-03-2011
"""
from django.shortcuts import render_to_response
from django.template import RequestContext
from Seed.modeler.forms import NewForm
from Seed.ws_client.beekeeperws import upload_new_form
from base64 import b64encode

#from xml.etree.ElementTree import tostring, XML

def showModeler(request):
    context = RequestContext(request)
    if request.method == 'POST':
        form = NewForm(request.POST)
        if form.is_valid() :
            xmlForm = form.cleaned_data['fieldList']
            xmlFormCleaned = b64encode(xmlForm)
            response = upload_new_form(xmlFormCleaned)
            context['prueba'] = xmlForm
            context['prueba2'] = xmlFormCleaned
            context['wsresponse'] = response
#        if form.is_valid() :
#            return HttpResponseRedirect('/formList')
    else:
        form = NewForm()
        
    context['form'] = form
    return render_to_response('create.html', context)

#def parseSentFormXML (xml):
#    if xml is None:
#        return None
#    else:
#        #myparser = parseString(xml)
#        #sections = formXML.findall("section")
#        return xml;


