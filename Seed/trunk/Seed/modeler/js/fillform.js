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
var formName = "Nombre del formulario";
var geolocalized = false;
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

$("#geolocalization").change(function() {
	geolocalized = $(this).is(':checked');
});

/*****************************************************************/
/* Crea un nuevo campo para el formulario                        */
/*****************************************************************/
function createNewField(id, name, section, idDrag, type, fieldType) {
	// Componemos el id del nuevo campo
	var idField = 's' + section + '-f' + formSections[section].size();
	var properties;
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
				$('<img />', {
					src: 'images/buttons/not-required.png',
					onClick: 'javascript:setRequired(this, \''+idField+'\')',
					class: 'fieldButton'
				}),
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
		properties = $('<div />', {
	    	class: 'properties',
	    	text: 'PROPIEDADES'
		})
	);
	// Añadimos los listeners al elemento
	addListenersToField(newField);
	// Variable para almacenar el nuevo campo dado de alta
	var jsField;
	// Si se trata de un combo o un radio
	if ((type == "radio") || (type == "combo")) {
		// Agregamos las opciones
		newField.append($('<div />', {
	    	class: 'options',
	    	text: 'OPCIONES'
		}).append(
				$('<br />'),
				// Añadir nueva opción
				$('<img />', {
					src: 'images/buttons/add.png',
					onClick: 'javascript:addOptionToField(this.parentNode, \''+idField+'\')',
					class: 'addoptionbutton'
				})
		));
		if (type == "radio")
			jsField = new Radio(name,id,fieldType);
	}
	else { // Cualquier otro tipo de campo
		jsField = new Field(name,id,fieldType);
	} 
    formSections[section].addField(jsField);
	// Añadimos las propiedades al campo
	addPropertiesToField(idField, properties, type);
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
function setRequired(image, tagID) {
   var auxIDs = tagID.match(/\d+/g);
   var sectionID = parseInt(auxIDs[0]);
   var fieldID = parseInt(auxIDs[1]);
   var campo = formSections[sectionID].fields[fieldID];
   if (campo.required == true){   
	   $(image).attr("src", "images/buttons/not-required.png");
	   campo.setRequired(false);
   }
   else {
	   $(image).attr("src", "images/buttons/required.png")
	   campo.setRequired(true);
   }
}

// Hacer etiqueta y valor de la opción editables
function makeOptionEditable(optag, sID, fID, oID) {
	//Label de la opción editable
	var label = $("#" + optag + " .optionlabel");
	label.inlineEdit({
			save: function(event, hash) {
					var option = formSections[sID].fields[fID].options[oID];
					option.label = hash.value;
			}
	});
	//Value de la opción editable
	var value = $("#" + optag + " .optionvalue")
	value.inlineEdit({
		save: function(event, hash) {
				var option = formSections[sID].fields[fID].options[oID];
				option.value = hash.value;
		}
	});
}

//Hacer etiqueta y valor de la opción editables
function makePropertyEditable(propTag, sID, fID, pID) {
	//Value de la propiedad editable
	var value = $("#" + propTag + " .propertyvalue")
	value.inlineEdit({
		save: function(event, hash) {
				var property = formSections[sID].fields[fID].properties[pID];
				property.value = hash.value;
		}
	});
}

// Genera el elemento DOM para una propiedad normal
function addPropertyToDOM (div, propTag, propText, propValue) {
	$(div).append($('<p />',{
		class: 'property',
		id: propTag
	}).append($('<span />', {
		text: propText,
		class: 'propertylabel',
	}),
	$('<span />', {
		text: propValue,
		class: 'propertyvalue',
	})
	));	
}

function addPropertyCheckToDOM (div, propTag, propText) {
	var check;
	$(div).append($('<p />',{
		class: 'property',
		id: propTag
	}).append($('<span />', {
		text: propText,
		class: 'propertylabel',
	}),
	check = $('<input/>', {
		type: "checkbox",
		class: 'propertyvalue',
	})
	));
	return check;
}

//Añadir propiedades al campo
function addPropertiesToField (idField, div, type) {
	var auxIDs = idField.match(/\d+/g);
	var sID = parseInt(auxIDs[0]);
	var fID = parseInt(auxIDs[1]);
	if (type == "text") {
		var pID = formSections[sID].fields[fID].addProperty("MAX_LENGTH", "100");
		var propTag = 's' + sID + 'f' + fID + 'p' + pID;
		addPropertyToDOM(div, propTag, "MAX_LENGTH", "100");
		makePropertyEditable(propTag, sID, fID, pID);
		pID = formSections[sID].fields[fID].addPropertyCheck("EMAIL", false);
		propTag = 's' + sID + 'f' + fID + 'p' + pID;
		var divCheck = addPropertyCheckToDOM(div, propTag, "EMAIL");
		$(divCheck).change(function() {
				var checkTag = this.parentNode.id;
				var auxIDs = checkTag.match(/\d+/g);
				var sIDaux = parseInt(auxIDs[0]);
				var fIDaux = parseInt(auxIDs[1]);
				var pIDaux = parseInt(auxIDs[2]);
				formSections[sIDaux].fields[fIDaux].properties[pIDaux].value = $(this).is(':checked');
			});
	}
}


// Añadir opción al campo
function addOptionToField (divOptions, tagID) {
	var auxIDs = tagID.match(/\d+/g);
	var sID = parseInt(auxIDs[0]);
	var fID = parseInt(auxIDs[1]);
	var field = formSections[sID].fields[fID];
	var opID = field.addOption('label', 'value');
	var pOptions;
	var opTag = 's' + sID + 'f' + fID + 'o' + opID;
	$(divOptions).append (
		pOptions = $('<p />', {
						id: opTag,
						class: "option"
					}).prepend(
						$('<span />', {
							text: 'Opción',
							class: 'optionlabel',
						}),
						$('<span />', {
							text: ':',
						}),
						$('<span />', {
							text: 'Valor',
							class: 'optionvalue',
						}),
						// Botón de borrado
						$('<img />', {
							src  : 'images/buttons/delete.png',
							onClick: 'javascript:deleteOption(this.parentNode)',
							class: 'optionbutton'
						})
					)
	);
	makeOptionEditable(opTag, sID, fID, opID)
}

function deleteOption (pOptions) {
	var auxIDs = (pOptions.id).match(/\d+/g);
	var sID = parseInt(auxIDs[0]);
	var fID = parseInt(auxIDs[1]);
	var oID = parseInt(auxIDs[2]);
	formSections[sID].fields[fID].removeOption(oID);
	deleteParentElement(pOptions, 'p');	
	}

//Mostrar propiedades (y opciones)
function expandField (tagID) {
   var node = document.querySelector('#'+tagID);
   node = $(node).children('.properties, .options');
   $(node).slideToggle('fast');
}

// Añadir una nueva sección
function addSection () {
	var name = 'Nombre de la sección ' + (++actualSection);
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
			}).append(
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