"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

from xml.etree.ElementTree import ElementTree
from BeeKeeper.db_models.models import Section, Form


def parseTextField(parser):
    id = parser.find('id')
    label = parser.findtext('label')
    maxlength = parser.findtext('maxlength')
    required = parser.findtext('required')
    value = parser.findtext('value')


def parseDateField(parser):
    id = parser.find('id')
    label = parser.findtext('label')
    required = parser.findtext('required')
    day = parser.findtext('date_value/day')
    day = parser.findtext('date_value/month')
    day = parser.findtext('date_value/year')


def parseRadioField(parser):
    id = parser.find('id')
    label = parser.findtext('label')
    required = parser.findtext('required')
    
    options = parser.findall("option")
    for option in options:
        optionLabel = option.findtext("label")
        optionValue = option.findtext("value")
        optionSelected = option.findtext("selected")
            <radio><!-- Elementos tipo radio-button -->
                <id>id</id>
                <label>Sexo</label>
                <required>false</required>
                <options>
                    <option>
                        <label>Masculino</label>
                        <value>M</value>
                        <selected/>
                    </option>
                    <option>
                        <label>Femenino</label>
                        <value>F</value>
                    </option>
                </options>
            </radio>


def parseComboField(parser):
    id = parser.find('id')
    label = parser.findtext('label')
    required = parser.findtext('required')
    
    options = parser.findall("option")
    for option in options:
        optionLabel = option.findtext("label")
        optionValue = option.findtext("value")
        optionSelected = option.findtext("selected")


## Data types ##
actionSwitch = {
    'text': parseTextField,
    'date': parseDateField,
    'radio': parseRadioField,
    'combo': parseComboField
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
        form = Form(version=version, name=name)
        form.save()
        id.text = form.id
        
        #Section
        sections = parser.findall("section")
        for section in sections:
            id = section.find("id")
            name = section.findtext("name")
            section_model = Section(name=name, order="TO-DO")
            #Section fields
            for field in section:
                actionSwitch[field.tag](field)
            
        # Saving the XML in the form table
        form.xml = tostring(parser.getroot)
        form.save()