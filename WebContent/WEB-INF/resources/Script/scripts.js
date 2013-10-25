// JavaScript Document

  $("window").load(function() {
    	  $("#body").removeClass("preload");
    	
    	});


function UpdateTableHeaders() {
   $(".persist-area").each(function() {
   
       var el             = $(this),
           offset         = el.offset(),
           scrollTop      = $(window).scrollTop(),
           floatingHeader = $(".floatingHeader", this)
       
       if ((scrollTop > offset.top) && (scrollTop < offset.top + el.height())) {
           floatingHeader.css({
            "visibility": "visible"
           });
       } else {
           floatingHeader.css({
            "visibility": "hidden"
           });      
       };
   });
}

// DOM Ready      
$(function() {

   var clonedHeaderRow;

   $(".persist-area").each(function() {
       clonedHeaderRow = $(".persist-header", this);
       clonedHeaderRow
         .before(clonedHeaderRow.clone())
         .css("width", clonedHeaderRow.width())
         .addClass("floatingHeader");
         
   });
   
   $(window)
    .scroll(UpdateTableHeaders)
    .trigger("scroll");
   
});

 
  function showText(){
      if(document.getElementById("mainSearchBar0").value == ""){
          document.getElementById("mainSearchBar0").value="Type in a word";
          document.getElementById("mainSearchBar0").style.color = "#777";}}
      function hideText(){
              if(document.getElementById("mainSearchBar0").value == "Type in a word"){
                  document.getElementById("mainSearchBar0").value="";
                  document.getElementById("mainSearchBar0").style.color="#000";
                  }
                  }

      
    
    			$(".share-btn").mouseenter(function() {
    				setTimeout(function() {
    				$(".item-menu").addClass("visible")
    				}, 500);
    			});
    			$(".share-btn").mouseleave(function() {
    				setTimeout(function() {
    				$(".item-menu").removeClass("visible")
    				}, 500);
    			});
    			$(".item-menu").hover(function() {
    				$(".item-menu").addClass("visible")
    			});
    			$(".item-menu").mouseleave(function() {
    				setTimeout(function() {
    				$(".item-menu").removeClass("visible")
    				}, 500);
    			});
    			$(".container-item").hover(function() {
    				setTimeout(function() {
    				$(".container-item").css("z-index","1000")
    				}, 500);
    			});
