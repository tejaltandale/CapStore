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

    			
    			$(function() {
    			      $('#slides').slidesjs({
    			        width: 980,
    			        height: 528,
    			        play: {
    			          active: true,
    			          auto: true,
    			         interval: 4000,
    			          swap: true
    			        }
    			      });
    			    });		
    			 function setdata(tmp){
    			  		document.getElementById("hidd").value=tmp.value;
    			  	}

    			  	function setrate(tmp1){
    			  		/*alert(tmp1.value);*/
    			  		document.getElementById("hrate").value=tmp1.value;
    			  	}
    			/*  	$(document).ready(function(){           
    			  		 //Stops the submit request=
    			  		$(document).on('click', '.usercheck',function(e){
    			  			 document.getElementById("hidd").value=this.value;
    			  			 document.getElementById("content1").value=""; 
    			  		 });*/
    			  	    
    			  	/*$(document).ready(function(){
    			  		 document.getElementById("hidd").value=this.value;
			  			 document.getElementById("content1").value=""; 
    			  	$("#post_feedback").click(function(e){
    			  	    	
    			  	    	alert("aditya");
    			  	        var rateString =document.getElementById("hrate").value;
    			  	        alert(rateString);
    			  	        var feedString =document.getElementById("content1").value;
    			  	        alert("alert content");
    			  	        if(document.getElementById("content1").value.length==0){
    			  	        	alert("Enter Feedback COntent");
    			  	        }      
    			  	        else{
    			  	        $.ajax({
    			  	            type: "GET",
    			  	            url: "postfeedback",
    			  	            data: {"rateString":rateString,"feedString":feedString},
    			  	            dataType: 'jsonp',
    			  	            jsonp: 'callback',
    			  	            jsonpCallback: 'jsonpCallback',
    			  	            //if received a response from the server
    			  	            success: function( data, textStatus, jqXHR) {
    			  	                //our country code was correct so we have some information to display
    			  	           	 $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
    			  	           	document.getElementById("content1").value="";
    			  	            },
    			  	            //If there was no resonse from the server
    			  	            error: function(jqXHR, textStatus, errorThrown){
    			  	                 console.log("Something really bad happened " + textStatus);
    			  	                 $("#ajaxResponse").html(jqXHR.responseText);
    			  	                 document.getElementById("content1").value="";
    			  	            },
    			  	            //capture the request before it was sent to server
    			  	            beforeSend: function(jqXHR, settings){
    			  	                //disable the button until we get the response
    			  	                $('#myButton').attr("disabled", true);
    			  	                document.getElementById("content1").value="";
    			  	            },
    			  	            //this is called after the response or error functions are finsihed
    			  	            //so that we can take some action
    			  	            complete: function(jqXHR, textStatus){
    			  	                //enable the button 
    			  	                $('#myButton').attr("disabled", false);
    			  	            }
    			  	        });       
    			  	        }  
    			  	 });
    			  	 $("#deleteme").click(function(e){
 			  	        var dataString =hidd.value;
 			  	        alert(dataString);
 			  	        if(document.getElementById("hidd").value.length==0){
 			  	        	alert("Select Data To delete");
 			  	        }     
 			  	        else{
 			  	        $.ajax({
 			  	            type: "GET",
 			  	            url: "deletefeedback",
 			  	            data: {"dataString":dataString},
 			  	            dataType: 'jsonp',
 			  	            jsonp: 'callback',
 			  	            jsonpCallback: 'jsonpCallback',
 			  	                     //If there was no resonse from the server
 			  	            error: function(jqXHR, textStatus, errorThrown){
 			  	                 $("#ajaxResponse").html(jqXHR.responseText);
 			  	                 document.getElementById("hidd").value="";
 			  	            },
 			  	             
 			  	          
 			  	        });       
 			  	        
 			  	        }      
 			  	});
 			  	    $("#show_feedback").click(function(e){
 			  	    	
 			  	     
 			  	        $.ajax({
 			  	            type: "GET",
 			  	            url: "view1feedback",
 			  	            data: {"dataString":"2"},
 			  	            dataType: 'jsonp',
 			  	            jsonp: 'callback',
 			  	            jsonpCallback: 'jsonpCallback',
 			  	             
 			  	                       error: function(jqXHR, textStatus, errorThrown){
 			  	                 $("#ajaxResponse").html(jqXHR.responseText);
 			  	            },
 			  	             
 			  	           
 			  	        });                  
 			  	});
    			  	});*/
    			  	    $(".link1").click(function(event){
    			  	        event.preventDefault();
    			  	        var url =$(this).attr("href");
    			  	        $('#right-pane').load(url);
    			  	     });
    			  		  //make the AJAX request, dataType is set to json
    			  		    //meaning we are expecting JSON data in response from the server
    			  		    $.ajax({
    			  		        type: "GET",
    			  		        url: "getSuggestions",
    			  		        dataType: 'jsonp',
    			  		        jsonp: 'callback',
    			  		        jsonpCallback: 'jsonpCallback',
    			  		        error: function(jqXHR, textStatus, errorThrown){
    			  		              $("#ajaxResponse2").html(jqXHR.responseText);
    			  		        },
    			  		         
    			  		    
    			  		    });         
    			  		
    			  		
    			  /*	});*/
    			
    			
    			