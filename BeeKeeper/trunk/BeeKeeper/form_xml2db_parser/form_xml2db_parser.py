"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

#Dump data

from xml.etree.ElementTree import ElementTree, tostring, XML
from BeeKeeper.db_models.models import Section, Form, FormField, FieldOption, Property, FieldGroup
from BeeKeeper.logger import *

import sys


### GLOBAL VARIABLE ###
logger = getlogger('FormXmldbParser')


class FormXmldbParser():
    

    ### ATRIBUTES ###
    
    ### METHODS ###

    def parse_group_properties(self, properties, group_model):
        for property in properties:
            property_name = property.findtext("name")
            property_value = property.findtext("value")
            property_model = Property(name=property_name, value=property_value, group_field=group_model)
            property_model.save()
        
        return 0
    
    def parse_field_properties(self, properties, field_model):
        for property in properties:
            property_name = property.findtext("name")
            property_value = property.findtext("value")
            property_model = Property(name=property_name, value=property_value, form_field=field_model)
            property_model.save()
        
        return 0
    
        
    def parse_field_options(self, options, field_model):
        for option in options:
            option_label = option.findtext("label")
            option_value = option.findtext("value")
            option_model = FieldOption(label=option_label, value=option_value, form_field=field_model)
            option_model.save()
        
        return 0
    
    
    def parse_text_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        
        return field_model
    
    
    def parse_textarea_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        
        return field_model
    
    
    def parse_date_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        
        return field_model
    
    
    def parse_radio_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        field_model.save()
        # Parsing
        options = parser.findall("options/option")
        self.parse_field_options(options, field_model)
        
        return field_model
    
    
    def parse_combo_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        field_model.save()
        # Parsing
        options = parser.findall("options/option")
        self.parse_field_options(options, field_model)
                
        return field_model
    
    
    def parse_check_field(self, parser, section_model):
        return FormField(section=section_model)


    def parse_generic_field(self, fields, section_model, i, j, field_group_model=None):
        """
        :attention The loop iterators are:
                   "i" for sections; "j" for fields in sections; "k" for fields in groups
        """
        ## Data types ##
        actionSwitch = {
            'TEXT': self.parse_text_field,
            'TEXTAREA': self.parse_textarea_field,
            'DATE': self.parse_date_field,
            'RADIO': self.parse_radio_field,
            'COMBO': self.parse_combo_field,
            'CHECKBOX': self.parse_check_field
            }
        # Parsing
        k = 0
        for field in fields:
            id = field.find('id')
            type = field.findtext('type')
            ok_type = False
            # TO-DO: Need to check that type is valid
            # If there is a an error we report it to the logger
            try:
                field_model = actionSwitch[type](field, section_model)
                ok_type = True
            except:
                logger.error('Indexing actionSwitch error:'+ str(sys.exc_info()[0]))
                # We sould rollack the transaction
            if ok_type:
                field_model.type = type
                field_model.section_order = j
                field_model.label = field.findtext('label')
                field_model.required = field.findtext('required')
                if field_model.required:
                    field_model.required = True
                else:
                    field_model.required = False
                # Groups
                if field_group_model:
                    field_model.field_group  = field_group_model
                    field_model.field_group_order  = k
                    # if the group is required, the fields are required
                    if field_group_model.required:
                        field_model.required = True
                    k += 1
                field_model.save()
                id.text = str(field_model.id)
                # Adding the field properties
                properties = field.findall("properties/property")
                self.parse_field_properties(properties, field_model)
            j += 1
            
        return j


    def parse_generic_group(self, groups, section_model, i, j):
        # Parsing
        for group in groups:
            id = group.find('id')
            label = group.findtext("label")
            # If required --> Special case
            required = group.findtext("required")
            if required == '': required = True
            else: required = False
            # If list --> Special case
            list = group.findtext("list")
            if list == '': list = True
            else: list = False
            ## Creating Group and List ##
            field_group_model = FieldGroup(label=label, required=required, multiple=list)
            field_group_model.save()
            id.text = str(field_group_model.id)
            # Group properties
            properties = group.findall("properties/property")
            self.parse_group_properties(properties, field_group_model)
            # Field
            fields = group.findall("field")
            j = self.parse_generic_field(fields, section_model, i, j, field_group_model)
            
        return j

    
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
