"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

#Dump data

from xml.etree.ElementTree import ElementTree
from BeeKeeper.db_models.models import Section, Form, FormField


def parse_text_field(parser):
    maxlength = parser.findtext('maxlength')
    value = parser.findtext('value')
    field_model = FormField(maxlength=maxlength, value=value)
    
    return field_model


def parse_date_field(parser):
    day = parser.findtext('date_value/day')
    month = parser.findtext('date_value/month')
    year = parser.findtext('date_value/year')
    field_model = FormField(day=day, month=month, year=year)
    
    return field_model


def parse_radio_field(parser):
    options = parser.findall("option")
    for option in options:
        optionLabel = option.findtext("label")
        optionValue = option.findtext("value")
        optionSelected = option.findtext("selected")
    
    return field_model


def parse_combo_field(parser):
    options = parser.findall("option")
    for option in options:
        optionLabel = option.findtext("label")
        optionValue = option.findtext("value")
        optionSelected = option.findtext("selected")
    
    return field_model


def parse_check_field(parser):
    return FormField()


## Data types ##
actionSwitch = {
    'text': parse_text_field,
    'date': parse_date_field,
    'radio': parse_radio_field,
    'combo': parse_combo_field,
    'checkbox': parse_check_field
    }

def generateModels(xml):
    """
    """
    if xml is None:
        return None
    else:
        parser = ElementTree()
        parser.parse(xml)
        #Starting the parsing
        id = parser.find('id')
        version = parser.findtext('version')
        name = parser.findtext('name')
        user = parser.findtext('author/user')
        # Form model
        form_model = Form(version=version, name=name)
        form_model.save()
        id.text = form_model.id
        
        #Section
        sections = parser.findall("section")
        for i, section in sections:
            id = section.find('id')
            name = section.findtext("name")
            section_model = Section(name=name, order=i)
            section_model.save()
            id.text = section_model.id
            #Section fields
            for i, field in section:
                id = field.find('id')
                field_model = actionSwitch[field.tag](field)
                field_model.order = i
                field_model.label = field.findtext('label')
                field_model.required = field.findtext('required')
                field_model.save()
                id.text = field_model.id
            
        # Saving the XML in the form table
        form_model.xml = tostring(parser.getroot)
        form_model.save()