# Create your views here.
from django.shortcuts import render_to_response

def form_detals (request, pais_origen, ciudad_origen, pais_destino, ciudad_destino):
    
    return render_to_response('form_details.html', {})
