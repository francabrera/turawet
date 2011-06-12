/**
* @fileoverview Funciones para la creación, eliminación,
* 				y manejo de los modelos.
*
* @author Francisco Jose Cabrera Hernandez, Nicolas Pernas Maradei, Romen Rodriguez Gil
* @version 0.1
*/

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
			formName = hash.value;
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
				// Botón de requerido/no requerido
				/*$('<div />', {
					onClick: 'javascript:setRequired(\''+idField+'\')',
					class: 'fieldNotRequired'
				}),*/
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

// Establecer la obligatoriedad o no del campo
function setRequired(tagID) {
   var node = document.querySelector('#'+tagID);
   var auxIDs = tagID.match(/\d+/g);
   var sectionID = parseInt(auxIDs[0]);
   var fieldID = parseInt(auxIDs[1])
   if ($(node, "div.actions .fieldRequired").length > 0) {
	   var image = $(node, "div.actions .fieldRequired");
	   $(image).addClass("fieldNotRequired");
	   $(image).removeClass("fieldRequired");
	   formSections[sectionID].fields[fieldID].setRequired(false);
   }
   else if ($(node, "div.actions .fieldNotRequired").length > 0) {
	   var image = $(node, "div.actions .fieldNotRequired");
	   $(image).addClass("fieldRequired");
	   $(image).removeClass("fielNotdRequired");
	   formSections[sectionID].fields[fieldID].setRequired(true);
   }
		
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

// Resaltar sección seleccionada
function selectSection (id) {
	var selectedSection = $('#s' + id);
	selectedSection.addClass("selected");
	setTimeout("$('#s" + id + "').removeClass(\"selected\");",1500);
}