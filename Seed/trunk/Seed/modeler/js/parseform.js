/**
* @fileoverview Parser del formulario a XML.
* 
* @author Francisco Jose Cabrera Hernandez, Nicolas Pernas Maradei, Romen Rodriguez Gil
* @version 0.1
*/

/*****************************************************************/
/* Devuelve el String con el XML del formulario form             */
/*****************************************************************/

function formToXML (name, sections) {
	var text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	text += "<form><id/><meta><version>1</version>";
	text += "<name>" + name + "</name>";
	text += "<author><user>turawet</user></author></meta>";
	text += "<sections>";
	var sCounter;
	for (sCounter=0;sCounter<sections.length;sCounter++)	
		text += sections[sCounter].toXMLOrdered();
	text += "</sections></form>";
	/*if (window.DOMParser) {
		parser=new DOMParser();
		xmlForm=parser.parseFromString(text,"text/xml");
		salida = (new XMLSerializer()).serializeToString(xmlForm);
	}
	else { // Internet Explorer
		xmlForm=new ActiveXObject("Microsoft.XMLDOM");
		xmlForm.async="false";
		xmlForm.loadXML(text); 
		salida = xmlForm.xml;
	}*/	
	return text;
}

function fillForm () {
	var xmlField = document.querySelector('#id_fieldList');
	xmlField.value = formToXML(formName, formSections);
}