/**
* @fileoverview Definición de clases y variables donde se 
* 				almacenará el formulario y sus elementos.
*
* @author Turawet Project
* @version 0.1
*/

/*****************************************************************/
/* Form classes                                                  */
/*****************************************************************/
/**
 * Clase campo
 */
function Field(name, order, type) {
	this.name = name;
	this.order = order;
	this.type = type;
	
	/**
	 * Devuelve el XML del campo
	 */
	this.toXML = function()
	{
		var fieldxml = "<field><id />";
		fieldxml += "<label>" + this.name + "</label>";
		fieldxml += "<type>"+ this.type +"</type>";
		fieldxml += "<properties/></field>";
		return fieldxml;
	}
	
}
/* -------------- */


/**
 * Clase sección
 */
function Section(name, order) {
	this.name = name;
	this.order = order;
	this.fields = new Array();
	
	/**
	 * Añadir campo a la sección
	 */
	this.addField = function(f)    
	{
	    this.fields.push(f);
	}
	
	this.removeField = function(id)    
	{
	    delete this.fields[id];
	}
	
	this.changeFieldName = function(id, newlabel)
	{
		if (typeof this.fields[id]  != "undefined")
			this.fields[id].name = newlabel;
	}
	
	/**
	 * Devuelve el número de campos de la sección
	 */
	this.size = function()    
	{
	    return this.fields.length;
	}
	
	/**
	 * Devuelve el XML de la sección con sus campos
	 */
	this.toXML = function()
	{
		var i;
		var xml = "<section><id/>";
		xml += "<name>" + this.name + "</name>";
		xml += "<fields>";
		for (i=0;i<this.fields.length;i++)	
			if (typeof this.fields[i] != "undefined")
				xml += this.fields[i].toXML();
		xml += "</fields></section>";
		return xml;
	}
	
	this.toXMLOrdered = function()
	{
		var i;
		var xml = "<section><id/>";
		xml += "<name>" + this.name + "</name>";
		xml += "<fields>";
		var sectionElems = $("#s" + this.order + " li");
		for (i=0;i<sectionElems.length;i++) {
			var fieldElem = sectionElems[i];
			// Los IDs de los elementos Field serán sX-fX
			// Nos quedamos con el segundo matching
			var j = parseInt((fieldElem.id).match(/\d+/g)[1]);
			xml += this.fields[j].toXML();
		}
		xml += "</fields></section>";
		return xml;
	}
		
}
/* -------------- */

/*****************************************************************/
/* Inicializacion                                                */
/*****************************************************************/
var formName = "Formulario";
var formSections = new Array();
formSections[0] = new Section("Nombre sección 0", 0);
var actualSection = 0;

/****** PRUEBAS ******/
function hideResults() {
	$('#resultado').toggle();
}
function showResults() {
	$('#resultado').toggle('slow');
}

/*********************/

/*****************************************************************/
/* Tipos de campos                                               */
/*****************************************************************/
var fieldTypes = {"text" : 0, "textarea" : 1, "file" : 3, "image_gallery" : 4};

//Etiquetas editables
$(".fieldlabel").inlineEdit({
		save: function(event, hash) {
				var fieldElem = this.parentNode;
				var aux = new Array();
				aux = (fieldElem.id).match(/\d+/g);
				var sectionId = parseInt(aux[0]);
				var fieldId = parseInt(aux[1]);
				formSections[sectionId].changeFieldName(fieldId, hash.value);
		}
});

//Nombres secciones editables
$(".sectionlabel").inlineEdit({
		save: function(event, hash) {
				var sectionElem = this.parentNode;
				var sectionId = parseInt((sectionElem.id).match(/\d+/));
				formSections[sectionId].name = hash.value;
		}
});

//Nombre formulario editable
$(".formname").inlineEdit({
		save: function(event, hash) {
			formname = hash.value;
		}
});

/*****************************************************************/
/* Crea un nuevo campo para el formulario                        */
/*****************************************************************/
function createNewField(id, name, section, idDrag, type, fieldType) {
	// Componemos el id del nuevo campo
	var idField = 's' + section + '-f' + formSections[section].size();
	var newField = $('<li />', {
        class: type,
        id: idField,
        data : { id : idDrag }
    }).append(
        // Cuadro de botones
		$('<div />', {
	    	class: 'actions'
		}).prepend(
				// Botón de propiedades
				$('<img />', {
					src  : 'images/buttons/more.png',
					onClick: 'javascript:expandField(\''+idField+'\')',
					class: 'fieldButton'
				}),
				// Botón de borrado
				$('<img />', {
					src  : 'images/buttons/delete.png',
					onClick: 'javascript:deleteField(\''+idField+'\')',
					class: 'fieldButton'
				})
		),
		// Nombre del campo
	    $('<p />', {
	    	text : name,
	    	class: 'fieldlabel'
	    }),
	    $('<br />'),
	    // Propiedades
		$('<div />', {
	    	class: 'properties',
	    	text: 'propiedades'
		})
	);
	addListenersToField(newField);
    var jsField = new Field(name,id,fieldType);
    formSections[section].addField(jsField);
    return newField;
}

// Borrado
function deleteField (tagID) {
   var node = document.querySelector('#'+tagID);
   var auxIDs = tagID.match(/\d+/g);
   var sectionID = parseInt(auxIDs[0]);
   var fieldID = parseInt(auxIDs[1]);
   //alert("ID de la sección:" + sectionID + "\n ID del campo:" + fieldID);
   formSections[sectionID].removeField(fieldID);
   deleteParentElement(node, 'li');
}

//Mostrar propiedades
function expandField (tagID) {
   var node = document.querySelector('#'+tagID);
   node = $(node).children('.properties');
   $(node).slideToggle('fast');
}

// Añadir una nueva sección
function addSection () {
	var name = 'Nombre sección ' + (++actualSection);
	formSections[actualSection] = new Section(name, actualSection);
	var newSection =  $('<ul />', {
    	id: 's' + actualSection,
    	class: 'section',
    	href: '#s' + actualSection
    }).prepend( $('<p />', {
    	text : name,
    	class: 'sectionlabel',
    }));
	if (actualSection <= 10) {
		$('#sections > ul').append(
			$('<li />', {
				id: 's' + actualSection + 'link',
			}).prepend(
				$('<a />', {
					text: 'Sección ' + actualSection,
					href: '#s' + actualSection,
					onclick: 'selectSection(' + actualSection
								+ ')',						
				})
			)
		);
	}
	newSection.appendTo(myform);
	addListenersToSection(newSection);
}

function selectSection (id) {
	$('#s' + id).addClass("selected");
	setTimeout("$('#s" + id + "').removeClass(\"selected\");",1500);
}