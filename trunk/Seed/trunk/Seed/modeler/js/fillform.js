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

/*****************************************************************/
/* Tipos de campos                                               */
/*****************************************************************/
var fieldTypes = {"text" : 0, "textarea" : 1, "file" : 3, "image_gallery" : 4};

/*****************************************************************/
/* Crea un nuevo campo para el formulario                        */
/*****************************************************************/
function createNewField(id, name, idDrag, type) {
	var newField = $('<li />', {
        text : name,
        class: type,
        data : { id : idDrag }
    })/*.append($('<img />', {
        'src' : 'images/text_field.png',
    }))*/;
    var jsField = new Field(name,id);
    formSections[0].addField(jsField);
    return newField;
}