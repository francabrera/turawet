# Create your views here.

from django.shortcuts import render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext

from BeeKeeper.form_xml2db_parser.form_xml2db_parser import FormXmldbParser 
import os.path

def try_form_xml2db_parser (request):
    # Creationg a parser
    parser = FormXmldbParser()
    # XML Sample
    xmlpath = os.path.dirname(__file__) + '/resources/formulario.xml'
    f = open(xmlpath, "r")
    xml = f.read()
    f.close()
    #print(xml)
    parser.generateModels(xml)
    
    return render_to_response('blank.html')