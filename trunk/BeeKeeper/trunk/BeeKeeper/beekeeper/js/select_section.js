// Resaltar sección seleccionada
function selectSection (id) {
	var selectedSection = $('#' + id);
	selectedSection.addClass("selected");
	setTimeout("$('#" + id + "').removeClass(\"selected\");",1500);
}