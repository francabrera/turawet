"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""
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
