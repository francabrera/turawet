/**
* @fileoverview Definición de clases donde se almacenará
* 				el formulario y sus elementos. Traducciones
* 				a XML de dichos elementos.
*
* @author Francisco Jose Cabrera Hernandez, Nicolas Pernas Maradei, Romen Rodriguez Gil
* @version 0.1
*/

// Inheritance
function surrogateCtor() {}

function extend(base, sub) {
  surrogateCtor.prototype = base.prototype;
  sub.prototype = new surrogateCtor();
  sub.prototype.constructor = sub;
}

/*****************************************************************/
/* Form classes                                                  */
/*****************************************************************/
/**
 * Clase opciones de campo
 */
function FieldOption (label, value) {
	this.label = label;
	this.value = value;
	
	this.toXML = function () {
		var optxml = "<option>";
		optxml += "<label>" + this.label + "</label>";
		optxml += "<value>" + this.value + "</value>";
		optxml += "</option>";
		return optxml;
	}
}

/**
 * Clase campo
 */
function Field(name, order, type) {
	this.name = name;
	this.order = order;
	this.type = type;
	this.required = false;
	
	this.setRequired = function (req)
	{
		this.required = req;
	}
	
	/**
	 * Devuelve el XML del campo
	 */
	this.toXML = function()
	{
		var fieldxml = "<field><id />";
		fieldxml += "<label>" + this.name + "</label>";
		fieldxml += "<type>"+ this.type +"</type>";
		if (this.required)
			fieldxml += "<required />";
		fieldxml += "<properties /></field>";
		return fieldxml;
	}
	
}
/* -------------- */

/**
 * Clase campo radio (Extiende a Field)
 */
function Radio (name, order, type) {
	this.options = new Array();
	Field.call(this, name, order, type);
	
	this.addOption = function (label, value)
	{
		var aux = new FieldOption(label, value);
		return this.options.push(aux) - 1;
	}
	
	this.removeOption = function (id)
	{
		delete this.options[id];
	}
	
	/**
	 * Devuelve el XML del campo
	 */
	this.toXML = function()
	{
		var fieldxml = "<field><id />";
		fieldxml += "<label>" + this.name + "</label>";
		fieldxml += "<type>"+ this.type +"</type>";
		if (this.required)
			fieldxml += "<required />";
		fieldxml += "<properties /><options>";
		for (i=0;i<this.options.length;i++)
			if (typeof this.options[i]  != "undefined")
				fieldxml += this.options[i].toXML();
		fieldxml += "</options></field>";
		return fieldxml;
	}
}
extend(Field,Radio);


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