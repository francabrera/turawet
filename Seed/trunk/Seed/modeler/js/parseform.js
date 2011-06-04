/**
* @fileoverview Parser del formulario a XML.
*
* @author Turawet Project
* @version 0.1
*/

/*****************************************************************/
/* Devuelve el String con el XML del formulario form             */
/*****************************************************************/

function formToXML (name, sections) {
	var text = "<form><id/><version>1</version>";
	text += "<name>" + name + "</name>";
	text += "<author><user>turawet</user></author>";
	text += "<sections>";
	for (i=0;i<sections.length;i++)	
		text += sections[i].toXML();
	text += "</sections></form>";
	var salida;
	if (window.DOMParser) {
		parser=new DOMParser();
		xmlForm=parser.parseFromString(text,"text/xml");
		salida = (new XMLSerializer()).serializeToString(xmlForm);
	}
	else { // Internet Explorer
		xmlForm=new ActiveXObject("Microsoft.XMLDOM");
		xmlForm.async="false";
		xmlForm.loadXML(text); 
		salida = xmlForm.xml;
	}	
	return salida;
}

function fillForm () {
	var xmlField = document.querySelector('#id_fieldList');
	xmlField.value = formToXML(formName, formSections);
}