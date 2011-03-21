"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

from xml.etree.ElementTree import ElementTree;


def parseTextField(parser):
    id = parser.findtext('id');
    label = parser.findtext('label');
    maxlength = parser.findtext('maxlength');
    required = parser.findtext('required');
    value = parser.findtext('value');


def parseDateField(parser):
    id = parser.findtext('id');
    label = parser.findtext('label');
    required = parser.findtext('required');
    day = parser.findtext('date_value/day');
    day = parser.findtext('date_value/month');
    day = parser.findtext('date_value/year');


def parseComboField(parser):
    id = parser.findtext('id');
    label = parser.findtext('label');
    required = parser.findtext('required');
    
    options = parser.findall("option");
    for option in options:
        optionLabel = option.findtext("label");
        optionValue = option.findtext("value");
        optionSelected = option.findtext("selected");


                <id>id</id>
                <label>Estudios</label>
                <required>true</required>
                <options>
                    <option>
                        <label>Ingeniería Informática</label>
                        <value>IM</value>
                        <selected>selected</selected>
                    </option>
                    <option>
                        <label>Medicina</label>
                        <value>M</value>
                    </option>
                    <option>
                        <label>Farmacia</label>
                        <value>F</value>
                    </option>
                </options>


""" Data types """
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
        parser = ElementTree();
        parser.parse(xml);
        """Comenzamos con el parser"""
        id = parser.findtext('id');
        version = parser.findtext('version');
        name = parser.findtext('name');
        user = parser.findtext('author/user');
        
        """Section"""
        sections = parser.findall("section");
        for section in sections:
            section.findtext("id");
            section.findtext("name");
            """Extraemos los campos dentro de la sección"""
            for field in section:
                actionSwitch[field.tag](field);
                
                

        