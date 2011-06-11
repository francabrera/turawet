'''
*Modeler Form* Request Modeler Form
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.1
'''
from django import forms

# Create your models here.

class NewForm(forms.Form):
    fieldList = forms.CharField()