"""
Views for Seed web modeler
Project: Turawet
Date: 01-03-2011
"""
from django.shortcuts import render_to_response
from django.template import RequestContext

def showModeler(request):
    return render_to_response('create.html', RequestContext(request))