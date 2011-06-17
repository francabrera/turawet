
// Instancias geolocalizadas
var markers = [
//    {
//      'name': 'Australia',
//      'location': [-25.274398, 133.775136],
//      'message': 'TEXTO1'
//    },
    {
      'name': 'España',
      'location': [40.463667, -3.74922],
      'message': 'TEXTO2' 
   },
    {
      'name': 'La Svizra',
      'location': [46.818188, 8.227512],
      'message': 'TEXTO3' 
   },
    {
      'name': 'France',
      'location': [46.227638, 2.213749],
      'message': 'TEXTO4' 
   },
    {
      'name': 'Ireland',
      'location': [53.41291, -8.24389],
      'message': 'TEXTO5' 
   },
    {
      'name': 'Italia',
      'location': [41.87194, 12.56738],
      'message': 'TEXTO6' 
   },
    {
      'name': 'United Kingdom',
      'location': [55.378051, -3.435973],
      'message': 'TEXTO7' 
   },
//    {
//      'name': 'United States of America',
//      'location': [37.09024, -95.712891],
//      'message': 'TEXTO8' 
//   },
//    {
//      'name': 'Singapore',
//      'location': [1.352083, 103.819836],
//      'message': 'TEXTO9' 
//   }
];


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