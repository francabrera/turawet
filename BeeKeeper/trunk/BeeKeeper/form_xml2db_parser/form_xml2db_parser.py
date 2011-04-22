"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

#Dump data

from xml.etree.ElementTree import ElementTree, tostring, XML
from BeeKeeper.db_models.models import Section, Form, FormField, FieldOption, FieldProperty


class FormXmldbParser():
    ### METHODS ###    
    def parse_text_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        
        return field_model
    
    
    def parse_date_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        # Parsing
        properties = parser.findall("properties/property")
        for property in properties:
            property_name = property.findtext("name")
            property_value = property.findtext("value")
            property_model = FieldProperty(name=property_name, value=property_value, form_field=field_model)
            property_model.save()
        
        return field_model
    
    
    def parse_radio_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        # Parsing
        options = parser.findall("options/option")
        for option in options:
            option_label = option.findtext("label")
            option_value = option.findtext("value")
            option_model = FieldOption(label=option_label, value=option_value, form_field=field_model)
            option_model.save()
        
        return field_model
    
    
    def parse_combo_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        # Parsing
        options = parser.findall("options/option")
        for option in options:
            option_label = option.findtext("label")
            option_value = option.findtext("value")
            option_model = FieldOption(label=option_label, value=option_value, form_field=field_model)
            option_model.save()
        
        return field_model
    
    
    def parse_check_field(self, parser, section_model):
        return FormField(section=section_model)

    
    def generateModels(self, xml):
        """
        """
        ## Data types ##
        actionSwitch = {
            'TEXT': self.parse_text_field,
            'DATE': self.parse_date_field,
            'RADIO': self.parse_radio_field,
            'COMBO': self.parse_combo_field,
            'CHECKBOX': self.parse_check_field
            }
    
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
                j = 0
                for field in fields:
                    id = field.find('id')
                    type = field.findtext("type")
                    # Need to check that type is valid
                    field_model = self.actionSwitch[type](field, section_model)
                    field_model.order = j
                    field_model.label = field.findtext('label')
                    field_model.required = field.findtext('required')
                    if field_model.required:
                        field_model.required = True
                    else:
                        field_model.required = False
                    field_model.save()
                    id.text = str(field_model.id)
                    j += 1
                i += 1
            # Saving the XML in the form table
            form_model.xml = tostring(parser)
            form_model.save()