"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

#Dump data

from xml.etree.ElementTree import ElementTree, tostring, XML
from BeeKeeper.db_models.models import Section, Form, FormField, FieldOption


class FormXmldbParser():
    ### METHODS ###    
    def parse_text_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        
        return field_model
    
    
    def parse_date_field(self, parser, section_model):
        format = parser.findtext("format")
        field_model = FormField(format=format, section=section_model)
        
        return field_model
    
    
    def parse_radio_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        # Parsing
        options = parser.findall("option")
        for option in options:
            id = option.find('id')
            option_label = option.findtext("label")
            option_model = FieldOption(label=option_label, form_field=field_model)
            option_model.save()
            id.text = option_model.id
        
        return field_model
    
    
    def parse_combo_field(self, parser, section_model):
        field_model = FormField(section=section_model)
        # Parsing
        options = parser.findall("option")
        for option in options:
            id = option.find('id')
            option_label = option.findtext("label")
            option_model = FieldOption(label=option_label, form_field=field_model)
            option_model.save()
            id.text = option_model.id
        
        return field_model
    
    
    def parse_check_field(self, parser, section_model):
        return FormField(section=section_model)

    
    def generateModels(self, xml):
        """
        """
        ## Data types ##
        actionSwitch = {
            'text': self.parse_text_field,
            'date': self.parse_date_field,
            'radio': self.parse_radio_field,
            'combo': self.parse_combo_field,
            'checkbox': self.parse_check_field
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
            ################
            f = open("archivo.txt", "w")
            f.write(str(sections))
            f.close()
            ################
            for i, section in sections:
                id = section.find('id')
                name = section.findtext("name")
                # NAME ES NULL ahora mismo y LA 'i' NO VALE
                section_model = Section(name=name, order=i, form=form_model)
                section_model.save()
                id.text = str(section_model.id)
                #Section fields
                for i, field in section:
                    id = field.find('id')
                    field_model = self.actionSwitch[field.tag](field, section_model)
                    field_model.order = i
                    field_model.label = field.findtext('label')
                    field_model.required = field.findtext('required')
                    field_model.save()
                    id.text = str(field_model.id)
                
            # Saving the XML in the form table
            form_model.xml = tostring(parser)
            ################
            ff = open("archivo2.txt", "w")
            ff.write(form_model.xml)
            ff.close()
            ################
            form_model.save()