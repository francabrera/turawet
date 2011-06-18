/**
 * Initializes the map and listeners.
 */
function initialize() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: new google.maps.LatLng(38, 15),
    zoom: 2,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });
  // Icono de la marca
  var image = '/beekeeper/images/logo_geolocalization.png';
  // Ventana con el texto a mostrar
  var infoWin = new google.maps.InfoWindow({
	  content: "Instancia"
  });
  // Recorremos las instancias
  if (markers) {
	for (var i = 0; i < markers.length; i++) {
        var details = markers[i];		
		markers[i] = new google.maps.Marker({
	      title: details.name,
	      position: new google.maps.LatLng(
	          details.location[0], details.location[1]),
	      icon: image,
	      map: map,
	      message: details.message
	    });
		// Añadimos el evento "click" a la marca para mostrar el globo
		// de información sobre la instancia
		google.maps.event.addListener(markers[i], 'click', function() {
			infoWin.setContent(this.message);
			infoWin.open(map,this);
		});
    }
	
	//  Centramos el mapa
	var bounds = new google.maps.LatLngBounds();
	// For each de JQUERY
	$.each(markers, function (index, marker) {
		bounds.extend(marker.position);
	});
	map.fitBounds(bounds);
  }

}

google.maps.event.addDomListener(window, 'load', initialize);