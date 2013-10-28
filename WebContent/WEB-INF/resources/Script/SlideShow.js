$(document).ready(function(){
	$('.slideshow').cycle({
		fx:'fade',
		pause:1,
		prev: '#prev',
		next:'#next',
		
	});
	
});

// SlidesJS Required: Initialize SlidesJS with a jQuery doc ready -->

  $(function() {
    $('#slides').slidesjs({
  	  width: 940,
        height: 528,
      play: {
        active: true,
        auto: true,
        interval: 4000,
        swap: true
      }
    });
  });

// End SlidesJS Required -->