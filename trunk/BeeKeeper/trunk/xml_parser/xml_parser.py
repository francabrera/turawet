"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

from xml.etree.ElementTree import ElementTree;

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
        