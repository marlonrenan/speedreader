

$(document).ready(function() {
	
	var sprayReader = new SprayReader('#spray_result');
	
	$('.start').click(function(event) {
		
		
		var inputText = $('.texto').text();
		var wpm = 300;

		sprayReader.setInput(inputText);
		sprayReader.setWpm(wpm);
		sprayReader.start();
		

		event.preventDefault();
	});

	$('.stop').click(function(event) {
		sprayReader.stop();

		event.preventDefault();
	});
});