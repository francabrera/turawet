/*****************************************************************/
/* Form classes                                                  */
/*****************************************************************/
/* FIELD CLASS */
function Field(name, order) {
	this.name = name;
	this.order = order;
}
/* -------------- *


/* SECTION CLASS */
function Section() {
	this.name;
	this.order;
	this.fields = new Array();
	
	function addField(f)    
	{
	    this.fields.push(f);
	}
	this.addField = addField;
}
/* -------------- */

var formSections = new Array();
formSections[0] = new Section();

/*****************************************************************/
/* Crea un nuevo campo para el formulario                        */
/*****************************************************************/
function createNewField(id, name, idDrag, type) {
	var newField = $('<li />', {
        text : name,
        data : { id : idDrag }
    }).prepend($('<span />', {
        'class' : 'quantity',
        text : id
    }));
    var jsField = new Field(name,id);
    formSections[0].addField(jsField);
    return newField;
}