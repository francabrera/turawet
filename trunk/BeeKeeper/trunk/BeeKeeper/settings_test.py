'''
Created on 01/03/2011

@author: nicopernas
'''
from settings import *

DATABASES = {
    'default':
    {
        'ENGINE':   'django.db.backends.sqlite3',
        'NAME':     ':memory:',
        'USER':     '',
        'PASSWORD': '',
        'HOST':     '',
        'PORT':     '',
    }

}
