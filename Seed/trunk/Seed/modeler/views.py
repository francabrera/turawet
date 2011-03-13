"""
Views for Seed web modeler
Project: Turawet
Date: 01-03-2011
"""
from django.http import HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext
from Seed.modeler.forms import NewForm

def showModeler(request):
    if request.method == 'POST':
        form = NewForm(request.POST)
        if form.is_valid() :
            return HttpResponseRedirect('/formList')
    else:
        form = NewForm()
        
    context = RequestContext(request)
    context['form'] = form
    return render_to_response('create.html', context)