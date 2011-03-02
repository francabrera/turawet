'''
Created on 02/03/2011

@author: franxu
'''
from django import forms

# Create your models here.

class createForm(forms.Form):
    fieldList = forms.CharField()