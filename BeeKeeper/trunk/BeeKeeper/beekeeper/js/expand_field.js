//Mostrar propiedades
function expandField (tagID) {
   var node = document.querySelector('#'+tagID);
   node = $(node).children('.properties, .options');
   $(node).slideToggle('fast');
}