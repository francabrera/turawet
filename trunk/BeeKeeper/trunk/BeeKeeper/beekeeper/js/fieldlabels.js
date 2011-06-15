	// HOVER
    $('#fieldsBar > ul > li > .item').hover(
		function () { $('div.label', this).fadeIn(); }, 
		function () { $('div.label', this).fadeOut(); }
	);