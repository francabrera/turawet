'''
Created on 08/02/2011

@author: nicopernas
'''

from soaplib.serializers.clazz import ClassSerializer
from soaplib.serializers.primitive import String, Integer
from models_ws import ModelsSerializer
from soaplib.serializers.primitive import *

#class Dummy(ClassSerializer):
#    class types:
#       entero = Integer
#        value = String

class Dummy():
    def __init__(self, value, ent):
        self.value = value
        self.entero = ent


class DummyWs(ModelsSerializer):
    class types:
        entero = Integer
        value = String

