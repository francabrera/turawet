'''
Created on 08/02/2011

@author: nicopernas
'''

from soaplib.serializers.clazz import ClassSerializer
from soaplib.serializers.primitive import String

class Dummy(ClassSerializer):
    class types:
        value = String
        
    def __init__(self, value):
        self.value = value
        
    def get_value(self):
        return self.value
    
    def set_value(self, value):
        self.value = value
        
