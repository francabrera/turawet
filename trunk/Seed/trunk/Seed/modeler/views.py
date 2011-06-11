'''
*Modeler Views* Views for Seed web modeler
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.1
'''
from django.shortcuts import render_to_response
from django.template import RequestContext
from Seed.modeler.forms import NewForm
from Seed.ws_client.beekeeperws import upload_new_form
from base64 import urlsafe_b64encode as sb64encode

#from xml.etree.ElementTree import tostring, XML

def showModeler(request):
    context = RequestContext(request)
    if request.method == 'POST':
        form = NewForm(request.POST)
        if form.is_valid() :
            xmlForm = form.cleaned_data['fieldList']
            xmlFormCleaned = sb64encode(xmlForm)
            errorMessage = ""
            try:
                response = upload_new_form(xmlFormCleaned)
            except:
                response = False
                errorMessage = "Error al enviar"
            #b64xml = sb64encode(unicode('<?xml version="1.0" encoding="UTF-8"?><form><id/><version>1</version><name>Formulario</name><author><user>turawet</user></author><sections><section><id/><name>Nombre seccion 0</name><fields><field><id /><label>Campo de texto</label><type>TEXT</type><properties/></field></fields></section></sections></form>'))
            #upload_new_form(b64xml)
            context['prueba'] = xmlForm
            context['prueba2'] = xmlFormCleaned
            context['wsresponse'] = response
            context['errormessage'] = errorMessage
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


