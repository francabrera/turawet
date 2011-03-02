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
    return newField;
}

/*****************************************************************/
/* Menú de campos                                                */
/*****************************************************************/
var myfields = document.querySelectorAll('#fields > li > .item');
for (var i = 0; i < myfields.length; i++) {
	// DRAGOVER
	addEvent(myfields[i], 'dragstart', function (e) {
    	e.dataTransfer.setData('text', this.id);
		$('h2').fadeIn('fast');
    });
	// HOVER
    $(myfields[i]).hover(
		function () { $('div', this).fadeIn(); }, 
		function () { $('div', this).fadeOut(); }
	);
}

/*****************************************************************/
/* Formulario                                                    */
/*****************************************************************/
var myform = document.querySelector('#form');
// DRAGOVER
addEvent(myform, 'dragover', function (evt) {
    if (evt.preventDefault) evt.preventDefault(); // allows us to drop
    this.className = 'over';
    return false;
  });

// DRAGENTER
addEvent(myform, 'dragenter', function (evt) {
    if (evt.preventDefault) evt.preventDefault();
    // to get IE to work
    this.className = 'over';
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

    $("h2").fadeOut('fast');
    
    // Variables para el nuevo campo
    var fieldName =  $('p:first', item).text();
    var id = parseInt(lis.length);
    // Creamos el nuevo campo
    var newField = createNewField(id, fieldName, idDrag, '');
    
    // Agregamos el campo al formulario
    newField.appendTo(formList);

    /*nuevoCampo.fadeIn('fast');*/
    return false;
});
