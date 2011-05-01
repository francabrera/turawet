/**
* @fileoverview Funcionalidad Drag and Drop del modelador web
*
* @author Turawet Project
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
for (var i = 0; i < myfields.length; i++) {
	/*var image = myfields[i].getElementsByTagName("img");*/
	// DRAGSTART
	addEvent(myfields[i], 'dragstart', function (e) {
    	e.dataTransfer.setData('text', this.id);
    	this.style.backgroundColor = '#C9676C';
    	/*e.dataTransfer.setDragImage(image, -10,-10);*/
		//$('h2').fadeIn('fast');
		$('#toEndText').fadeIn('fast');
    });
	// DRAGEND
	addEvent(myfields[i], 'dragend', function (e) {
    	this.style.removeProperty("background-color");
    	//$("h2").fadeOut('fast');
    	$('#toEndText').fadeOut('fast');
    });
	// HOVER
    $(myfields[i]).hover(
		function () { $('div.label', this).fadeIn(); }, 
		function () { $('div.label', this).fadeOut(); }
	);
}

/*****************************************************************/
/* Formulario                                                    */
/*****************************************************************/

var myform = document.querySelector('#form');
// Form's fields will be sortable
$( "#form ul" ).sortable();
$( "#form ul" ).disableSelection();
// DRAGOVER
addEvent(myform, 'dragover', function (evt) {
    if (evt.preventDefault) evt.preventDefault(); // allows us to drop
    //$('ul', this).className = 'over';
    return false;
  });

// DRAGENTER
addEvent(myform, 'dragenter', function (evt) {
    if (evt.preventDefault) evt.preventDefault();
    // to get IE to work
    //this.className = 'over';
    return false;
});

// DROP
addEvent(myform, 'drop', function (evt) {
    if (evt.stopPropagation) evt.stopPropagation();
    // Obtenemos el ID transferido en el DRAG
    var idDrag = evt.dataTransfer.getData('text');
    var item = $('#' + idDrag);
    // Listado de campos del formulario
    var	formList = $("#form ul");
    var lis = $('li', formList);
    
    // Variables para el nuevo campo
    var fieldName =  $('p:first', item).text();
    var type =  $('div.type', item).text();
    var id = parseInt(lis.length);
    // Creamos el nuevo campo
    var newField = createNewField(id, fieldName, idDrag, type);
    
    // Agregamos el campo al formulario
    newField.appendTo(formList);

    /*nuevoCampo.fadeIn('fast');*/
    return false;
});

// DROP over a form field
function addListenersToField(field) {
	// DROP
	addEvent(field, 'drop', function (evt) {
	    if (evt.stopPropagation) evt.stopPropagation();
	    // Obtenemos el ID transferido en el DRAG
	    var idDrag = evt.dataTransfer.getData('text');
	    var item = $('#' + idDrag);
	    // Listado de campos del formulario
	    var	formList = $("#form ul");
	    var lis = $('li', formList);	    
	    // Variables para el nuevo campo
	    var fieldName =  $('p:first', item).text();
	    var type =  $('div.type', item).text();
	    var id = parseInt(lis.length);
	    // Creamos el nuevo campo
	    var newField = createNewField(id, fieldName, idDrag, type);	    
	    // Agregamos el campo al formulario
	    newField.insertBefore(this);

	    /*nuevoCampo.fadeIn('fast');*/
	    return false;
	});
}

/*****************************************************************/
/* Botones del campo                                             */
/*****************************************************************/
function deleteField (delNode) {
   var node = delNode;
   do {
      node = node.parentNode;
   }
   while
      (node.nodeType != 1 && node.nodeName != 'li');
  /* node.fadeOut('fast');*/
   node.parentNode.removeChild(node);
}
