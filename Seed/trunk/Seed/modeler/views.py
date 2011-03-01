"""
Views for Seed web modeler
Project: Turawet
Date: 01-03-2011
"""
from django.shortcuts import render_to_response

def showModeler(request):
    return render_to_response('modeler/create.html', context)