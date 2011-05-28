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
            
            #####
            # Creation date / Modification date
            #####
            
            # Form model
            form_model = Form(version=version, name=name)
            form_model.save()
            id.text = str(form_model.id)
            
            #Section
            sections = parser.findall("sections/section")
            i = 0;
            for section in sections:
                #id = section.find('id')
                name = section.findtext("name")
                # NAME ES NULL ahora mismo y LA 'i' NO VALE
                section_model = Section(name=name, order=i, form=form_model)
                section_model.save()
                id.text = str(section_model.id)
                #Section fields
                fields = section.findall("fields/field")
                # Parsing the fields
                j = 0 # Integer in python are inmutable. So they are passed by value to the function
                j = self.parse_generic_field(fields, section_model, i, j)
                # Parsing the groups
                groups = section.findall("fields/group")
                j = self.parse_generic_group(groups, section_model, i, j)
                i += 1
            # Saving the XML in the form table
            form_model.xml = tostring(parser)
            form_model.save()
            
            return 0