/**
* @fileoverview Funcionalidades Drag and Drop del modelador web
*
* @author Francisco Jose Cabrera Hernandez, Nicolas Pernas Maradei, Romen Rodriguez Gil
* @version 0.1
*/

/*****************************************************************/
/* Función para agregar Listeners a los eventos, IE compatible   */
/*****************************************************************/
var addEvent = (function () {
  if (document.addEventListener) {
    return function (el, type, fn) {
      if (el && el.nodeName || el === window) {
        el.addEventListener(type, fn, false);
      } else if (el && el.length) {
        for (var i = 0; i < el.length; i++) {
          addEvent(el[i], type, fn);
        }
      }
    };
  } else {
    /* Compatibilidad con IE y otros */
    return function (el, type, fn) {
      if (el && el.nodeName || el === window) {
        el.attachEvent('on' + type, function () { return fn.call(el, window.event); });
      } else if (el && el.length) {
        for (var i = 0; i < el.length; i++) {
          addEvent(el[i], type, fn);
        }
      }
    };
  }
})();


/*****************************************************************/
/* Menú de campos                                                */
/*****************************************************************/
var myfields = document.querySelectorAll('#fieldsBar > ul > li > .item');
$('#fieldsBar > div.buttons').disableSelection();
for (var i = 0; i < myfields.length; i++) {
	var actualField = myfields[i];
	// DRAGSTART
	addEvent(actualField, 'dragstart', function (e) {
    	e.dataTransfer.setData('text', this.id);
    	this.style.backgroundColor = '#C9676C';
    	e.dataTransfer.setDragImage(this.getElementsByTagName("img")[0], 10, 10);
		//$('h2').fadeIn('fast');
		$('#toEndText').fadeIn('fast');
    });
	// DRAGEND
	addEvent(actualField, 'dragend', function (e) {
    	this.style.removeProperty("background-color");
    	//$("h2").fadeOut('fast');
    	$('#toEndText').fadeOut('fast');
    });
	// HOVER
    $(actualField).hover(
		function () { $('div.label', this).fadeIn(); }, 
		function () { $('div.label', this).fadeOut(); }
	);
}

/*****************************************************************/
/* Formulario                                                    */
/*****************************************************************/

var myform = document.querySelector('#form');
// Form's fields will be sortable
$( "#form ul" ).sortable({items: "li:not(.toEnd)"});
$( "#form ul" ).disableSelection();
var mysections = document.querySelectorAll('#form ul');
for (var i = 0; i < mysections.length; i++) {
	var section = mysections[i];
	addListenersToSection(section);
}

// DROP over a form section
function addListenersToSection(section) {
	$(section).sortable({items: "li:not(.toEnd)"});
	$(section).disableSelection();
	// DRAGOVER
	addEvent(section, 'dragover', function (evt) {
	    if (evt.preventDefault) evt.preventDefault(); // allows us to drop
	    //$('ul', this).className = 'over';
	    return false;
	  });
	
	// DRAGENTER
	addEvent(section, 'dragenter', function (evt) {
	    if (evt.preventDefault) evt.preventDefault();
	    // to get IE to work
	    //this.className = 'over';
	    return false;
	});
	
	// DROP
	addEvent(section, 'drop', function (evt) {
	    if (evt.stopPropagation) evt.stopPropagation();
	    // Obtenemos el ID transferido en el DRAG
	    var idDrag = evt.dataTransfer.getData('text');
	    var item = $('#' + idDrag);
	    if ((typeof item != "undefined") && (item.length > 0)) {
		    // Listado de campos del formulario
		    var lis = $('li', this);
		    
		    // Variables para el nuevo campo
		    var fieldName =  $('p:first', item).text();
		    var type =  $('div.type', item).text();
		    var fieldType =  $('div.fieldtype', item).text();
		    // id of Section
		    var sectionId = parseInt((this.id).match(/\d+/));
		    // id of Field
		    var id = parseInt(formSections[sectionId].size());
		    // Creamos el nuevo campo
		    var newField = createNewField(id, fieldName, sectionId, idDrag, type, fieldType);
		    // Agregamos el campo al formulario
		    newField.appendTo(this);
		    
		    // Div contenedor del formulario
		    var divForm = $(this.parentNode);
		    var newTop = (divForm.scrollTop() + $(this).position().top
		    				+ $(this).outerHeight() - $(newField).outerHeight()*3);
		    // Hacemos scroll hasta el final de la sección
		    divForm.animate({scrollTop: newTop}, 700);
	    }
	    /*nuevoCampo.fadeIn('fast');*/
	    return false;
	});
}

// DROP over a form field
function addListenersToField(field) {
	// DROP
	addEvent(field, 'drop', function (evt) {
	    if (evt.stopPropagation) evt.stopPropagation();
	    // Obtenemos el ID transferido en el DRAG
	    var idDrag = evt.dataTransfer.getData('text');
	    var item = $('#' + idDrag);
	    if ((typeof item != "undefined") && (item.length > 0)) {
		    var lis = $('li', this.parentNode);	    
		    // Variables para el nuevo campo
		    var fieldName =  $('p:first', item).text();
		    var type =  $('div.type', item).text();
		    var fieldType =  $('div.fieldtype', item).text();
		    // id of Section
		    var sectionId = parseInt((this.parentNode.id).match(/\d+/));
		    // id of Field
		    var id = parseInt(formSections[sectionId].size());
		    // Creamos el nuevo campo
		    var newField = createNewField(id, fieldName, sectionId, idDrag, type, fieldType);	    
		    // Agregamos el campo al formulario
		    newField.insertBefore(this);
	    }
	    /*nuevoCampo.fadeIn('fast');*/
	    return false;
	});
}

/*****************************************************************/
/* Botones del campo                                             */
/*****************************************************************/
//Borra el elemento padre especificado del objeto item
function deleteParentElement (item, parentName) {
   var node = item;
   // Desde el nodo item, recorremos de forma ascendente hasta
   // encontrar al padre especificado
   while (node.nodeType != 1 && node.nodeName != parentName) {
      node = node.parentNode;
   };
   // Eliminamos el nodo hijo desde el nodo padre
   $(node).fadeOut('fast', function () {this.parentNode.removeChild(this)});
   
}
