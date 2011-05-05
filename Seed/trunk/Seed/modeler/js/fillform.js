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
function Field(name, order) {
	this.name = name;
	this.order = order;
	
	/**
	 * Devuelve el XML del campo
	 */
	this.toXML = function()
	{
		var fieldxml = "<field><order>" + this.order + "</order>";
		fieldxml += "<name>" + this.name + "</name></field>";
		return fieldxml;
	}
	
}
/* -------------- */


/**
 * Clase sección
 */
function Section() {
	this.name;
	this.order;
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
		var xml = "<section><order>" + this.order + "</order>";
		xml += "<name>" + this.order + "</name>";
		xml += "<fields>";
		for (i=0;i<this.fields.length;i++)			
			xml += this.fields[i].toXML();
		xml += "</fields></section>";
		return xml;
	}
}
/* -------------- */

var formSections = new Array();
formSections[0] = new Section();
var actualSection = 0;

/*****************************************************************/
/* Tipos de campos                                               */
/*****************************************************************/
var fieldTypes = {"text" : 0, "textarea" : 1, "file" : 3, "image_gallery" : 4};

/*****************************************************************/
/* Crea un nuevo campo para el formulario                        */
/*****************************************************************/
function createNewField(id, name, idDrag, type) {
	// Componemos el id del nuevo campo
	var idField = 's' + actualSection + '-f' + formSections[actualSection].size();
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
	    	class: 'label'
	    }),
	    // Propiedades
		$('<div />', {
	    	class: 'properties',
	    	text: 'propiedades'
		})
	);
	addListenersToField(newField);
    var jsField = new Field(name,id);
    formSections[0].addField(jsField);
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

//Borrado
function expandField (tagID) {
   var node = document.querySelector('#'+tagID);
   node = $(node).children('.properties');
   $(node).toggle();
}