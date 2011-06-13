//Mostrar propiedades
function expandField (tagID) {
   var node = document.querySelector('#'+tagID);
   node = $(node).children('.properties');
   $(node).slideToggle('fast');
}