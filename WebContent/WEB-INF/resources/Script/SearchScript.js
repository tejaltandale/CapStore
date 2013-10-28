$(document).ready(function(){

	$.ajax({
		type: "GET",
		url: "getInitialSearchData",
		dataType: 'jsonp',
		jsonp: 'callback',
		jsonpCallback: 'jsonpCallback',
		error: function(jqXHR, textStatus, errorThrown){
			console.log("Something really bad happened " + textStatus);
			$("#InitializeSearch").html(jqXHR.responseText);
		},
	});         
	$('.tsearch input[type=text]').on('keyup', function(e) {
		if (e.which == 13) {
			var searchdata=document.getElementById("query").value;
			alert(searchdata);

			$.ajax({
				type: "GET",
				url: "searchedProduct",
				data: {"searchdata":searchdata},
				dataType: 'jsonp',
				jsonp: 'callback',
				jsonpCallback: 'jsonpCallback',
				error: function(jqXHR, textStatus, errorThrown){
					$("#SearchedProduct").html(jqXHR.responseText);
				},
			}); 
		}
	});
	
	$("#searchbutton").click(function() {


		var searchdata=document.getElementById("query").value;

		alert(searchdata);


		$.ajax({

			type: "GET",

			url: "searchedProduct",

			data: {"searchdata":searchdata},

			dataType: 'jsonp',

			jsonp: 'callback',

			jsonpCallback: 'jsonpCallback',

			error: function(jqXHR, textStatus, errorThrown){

				$("#SearchedProduct").html(jqXHR.responseText);

			},

		}); 
	});

	$("#maxcost").bind('keyup mouseup',function(){
		var min1=document.getElementById("mincost").value;
		var max1=document.getElementById("maxcost").value;
		var brandString=document.getElementById("hiddenbrand").value;

		$.ajax({
			type: "GET",
			url: "getProductNamesPriceWise",
			data: {"minprice":min1,"maxprice":max1,"brandString":brandString},
			dataType: 'jsonp',
			jsonp: 'callback',
			jsonpCallback: 'jsonpCallback',
			error: function(jqXHR, textStatus, errorThrown){
				$("#AvailableProducts").html(jqXHR.responseText);
			},
		});         
	});


	$("#mincost").bind('keyup mouseup',function(){
		var min1=document.getElementById("mincost").value;
		var max1=document.getElementById("maxcost").value;
		var brandString=document.getElementById("hiddenbrand").value;
		$.ajax({
			type: "GET",
			url: "getProductNamesPriceWise",
			data: {"minprice":min1,"maxprice":max1,"brandString":brandString},
			dataType: 'jsonp',
			jsonp: 'callback',
			jsonpCallback: 'jsonpCallback',

			error: function(jqXHR, textStatus, errorThrown){
				$("#AvailableProducts").html(jqXHR.responseText);
			},
		});         
	});


	$(".types").change(function(e){
		document.getElementById("hiddenbrand").value="";
		document.getElementById("hiddentype").value=this.value;

		var dataString =this.value;
		var typeString=document.getElementById("hiddentype").value;

		$.ajax({
			type: "GET",
			url: "getBrandNames",
			data: {"dataString":dataString,"typeString":typeString},
			dataType: 'jsonp',
			jsonp: 'callback',
			jsonpCallback: 'jsonpCallback',
			error: function(jqXHR, textStatus, errorThrown){
				$("#AvailableBrands").html(jqXHR.responseText);
				$("#AvailableProducts").html("");
				$("#AvailableProducts").value("");   
			},    
		});       
	}); 
	$(document).on('change', '.bnames',function(e){
		document.getElementById("hiddenbrand").value= document.getElementById("hiddenbrand").value+this.value;
		var typeString=document.getElementById("hiddentype").value;
		var dataString;

		if(!this.checked){
			var r = new RegExp(this.value, 'g');
			var a=document.getElementById("hiddenbrand").value.replace(r, 'k');
			document.getElementById("hiddenbrand").value=a;
			dataString =a;
		}
		else{
			dataString =document.getElementById("hiddenbrand").value;

		}

		document.getElementById("anotherSection3").style.visibility="visible";
		//make the AJAX request, dataType is set to json
		//meaning we are expecting JSON data in response from the server
		$.ajax({
			type: "GET",
			url: "getProductNames",
			data: {"dataString":dataString,"typeString":typeString},
			dataType: 'jsonp',
			jsonp: 'callback',
			jsonpCallback: 'jsonpCallback',
			error: function(jqXHR, textStatus, errorThrown){
				$("#AvailableProducts").html("");
				$("#AvailableProducts").html(jqXHR.responseText);
			},
		});         
	});
	function jsonpCallback(data) {
		console.log("callback",data);
		//do nothing    
	}
	$(document).on('change', '.types11',function(e){

		document.getElementById("hiddenbrand").value= document.getElementById("hiddenbrand").value+this.value;
		var dataString;
		var typeString=this.value;
		if(!this.checked){
			var r = new RegExp(this.value, 'g');
			var a=document.getElementById("hiddenbrand").value.replace(r, 'kk');
			document.getElementById("hiddenbrand").value=a;
			dataString =a;

		}
		else{
			dataString =document.getElementById("hiddenbrand").value;
		} 

		document.getElementById("anotherSection3").style.visibility="visible";

		$.ajax({
			type: "GET",
			url: "getProductNamesBySortOrder111",
			//data: {"dataString":dataString,"typeString":typeString},
			data: {"dataString":dataString,"typeString":typeString},
			dataType: 'jsonp',
			jsonp: 'callback',
			jsonpCallback: 'jsonpCallback',


			//If there was no resonse from the server
			error: function(jqXHR, textStatus, errorThrown){
				$("#AvailableProducts").html(jqXHR.responseText);
			},


		});         
	});




});