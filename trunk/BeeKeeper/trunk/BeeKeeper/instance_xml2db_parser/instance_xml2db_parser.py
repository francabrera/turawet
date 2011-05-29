"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

#Dump data

from xml.etree.ElementTree import ElementTree, tostring, XML
from BeeKeeper.db_models.models import Instance
from BeeKeeper.logger import *

import sys


### GLOBAL VARIABLE ###
logger = getlogger('InstanceXmldbParser')


class InstanceXmldbParser():
    

    ### ATRIBUTES ###
    
    ### METHODS ###
    
    def generateModels(self, xml):
        if xml is None:
            return None
        else:
            parser = XML(xml)
            #Starting the parsing
            id = parser.find('id')
            version = parser.findtext('version')
            name = parser.findtext('name')
            user = parser.findtext('author/user')
            ####
            # Dates
            ####
            user = parser.findtext('creationdate')
            user = parser.findtext('modificationdate')
            
            
            #Section
            sections = parser.findall("sections/section")
            i = 0;
            for section in sections:
                ##### EL ID YA FUE INTRODUCIDO POR EL OTRO PARSER
                id = section.find('id')
                name = section.findtext("name")
                #### Obtenemos la sección que ya ha de estar guardada ####
                section_model = Section.objects.get(pk=id)
                #Section fields
                instance_fields = section.findall("instancefields/instancefield")
                # Parsing the fields
                j = 0 # Integer in python are inmutable. So they are passed by value to the function
                j = self.parse_generic_instancefield(instance_fields, section_model, i, j)
                # Parsing the groups
                groups = section.findall("instancefields/instancegroup")
                j = self.parse_generic_group(groups, section_model, i, j)
                i += 1
                #id.text = str(section_model.id)
            # Saving the XML in the form table
            #form_model.xml = tostring(parser)
            instance_model.save()
            
            return 0
            
            return 0