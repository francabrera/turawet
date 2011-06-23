# Create your views here.

from django.shortcuts import render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext

from BeeKeeper.instance_xml2db_parser.instance_xml2db_parser import InstanceXmldbParser 
import os.path

def instance_xml2db_parser_xml_big (request):
    # Creationg a parser
    parser = InstanceXmldbParser()
    # XML Sample
    xmlpath = os.path.dirname(__file__) + '/resources/instancia.xml'
    f = open(xmlpath, "r")
    xml = f.read()
    f.close()
    #print(xml)
    parser.generateModels(xml)
    
    return render_to_response('blank.html')



def instance_xml2db_parser_xml_p1 (request):
    # Creationg a parser
    parser = InstanceXmldbParser()
    # XML Sample
    xmlpath = os.path.dirname(__file__) + '/resources/instancia_prototipo1.xml'
    f = open(xmlpath, "r")
    xml = f.read()
    f.close()
    #print(xml)
    parser.generateModels(xml)
    
    return render_to_response('blank.html')