<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cap Store - Add Schemes</title>
<link type="text/css" href="resources/Css/login.css" rel="stylesheet">
<link type="text/css" href="resources/Css/style.css" rel="stylesheet">
<script src="resources/Script/login.js"></script>
<script src="resources/Script/scripts.js"></script>
<link type="text/css" href="resources/Css/jquery-ui.css"
	rel="stylesheet">
<script src="resources/Script/jquery-1.9.1.js"></script>
<script src="resources/Script/jquery-ui.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#product_id")
								.change(
										function() {
											document
													.getElementById('scheme_id').options.length = 0;
											var type1 = $("#product_id").val();
											alert(type1);
											$
													.ajax({
														type : "Get",
														url : "getAllSchemeByMerchantIdAndProductId",
														dataType : 'json',

														data : "productId="
																+ type1,
														success : function(data) {
															$('#scheme_id')
																	.append(
																			$(
																					'<option></option>')
																					.html(
																							"please select a scheme id"));
															$
																	.each(
																			data,
																			function(
																					index,
																					value) {

																				var obj = JSON
																						.parse(value);

																				$(
																						'#scheme_id')
																						.append(

																								$('<option value="'+obj.schemeId+'">'
																										+ obj.schemeDescription
																										+ '</option>')

																						);
																			});

														},
														error : function(e) {
															alert('Error: ' + e);
														}
													});

										});
					});

	function clickme() {

		alert($("#product_id").val());
		if ($("#product_id").val() == "Please Select a Product") {
			alert($("#product_id").val());
			return false;
		}
		if ($("#scheme_id").val() == "please select a scheme id") {
			alert($("#scheme_id").val());
			return false;
		}
		var sd = $("#datepicker").val();

		var ed = $("#datepicker1").val();
		var sdarray = sd.split("/");
		var edarray = ed.split("/");
		var s1 = sdarray[0];
		var s2 = sdarray[1];
		var s3 = sdarray[2];
		alert(sd);
		var e1 = edarray[0];
		var e2 = edarray[1];
		var e3 = edarray[2];

		if (((s2 < e2) && (s1 <= e1) && (s3 <= e3))
				|| ((s1 < e1) && (s3 <= e3)) || ((s3 < e3))) {
			return true;
		} else {
			alert("please enter end date greater than start date");
			return false;
		}
	}
	$(function() {
		$("#datepicker").datepicker({
			minDate : "0D",
			maxDate : "33Y 11M 12D"
		});
	});
	$(function() {
		$("#datepicker1").datepicker({
			minDate : "0D",
			maxDate : "33Y 11M 12D"
		});
	});
</script>
</head>
<body>
	<c:set var="is_created" value="${result}"></c:set>
	<c:if test="${is_created==true}">
		<script>
			alert("new offer successfully added");
		</script>
	</c:if>
	<!--Header Section-->

	<div class="header-tile"></div>
	<div class="header">
		<div class="header-title">
			<img src="resources/Images/fly.png" border="0" class="fly"
				height="3px" width="30px" /> <img src="resources/Images/fly.png"
				border="0" class="fly1" height="3px" width="30px" /> <img
				src="resources/Images/fly.png" border="0" class="fly2" height="3px"
				width="30px" />Cap Store
		</div>
		<!--Header Section->
<!--Search Bar Starts Here-->
		<form action="tsearch" method="get">
			<input type="text" class="mainSearchBar" list="awards"
				style="color: #000" title="Search" type="text" name="Search"
				onblur="showText()" onfocus="hideText();" value=""
				placeholder="       Search Here">

			<datalist id="awards" list="awards" class="datalist1"> <select>
				<option value="Best Picture"></option>
				<option value="Best Director"></option>
				<option value="Best Adapted Screenplay"></option>
				<option value="Best Original Screenplay"></option>
			</select> </datalist>

			<input class="mainSubmit" value=" " type="submit"
				style="position: relative; margin-left: -76px;">
		</form>
		<!--Search Bar Ends Here-->

		<!-- Logout Starts Here -->
		<div class="user">Welcome ${merchantname }</div>
		<a href="#" id="logout-button"><span>Logout</span><em></em></a>
		<!-- Logout Ends Here -->
	</div>


	<div id='cssmenu'>
		<ul>
			<li class='active'><a href='Home.jsp'><span>Home</span></a></li>
			<li><a href='#'><span>Mobiles @ Accessories</span></a></li>
			<li><a href='#'><span>Clothing</span></a></li>
			<li><a href='#'><span>Footwears</span></a></li>
			<li><a href='#'><span>Cosmetics</span></a></li>
			<li class='last'><a href='#'><span>Computers &
						Accessories</span></a></li>
		</ul>
	</div>
	<!--Header Section Ends Here-->

	<div class="navbox-sidebar">
		<ul class="nav-sidebar">
			<li><a href="addproductsbymerchant">Add Products</a></li>
			<li><a href="deleteproductbymerchant">Reduce Stocks</a></li>
			<li><a href="showproductsformerchant">Show Products</a></li>
			<li><a href="checkorders">Check Orders</a></li>
			<li><a href="discountsbyMerchant">Discounts</a></li>
		</ul>
	</div>

	<!-- MIDDLE CONTAINER starts here  -->

	<div class="middle-container">
		<div class="buttons">

			<a href="createOffer" class="offer">Create Offer</a> <a
				href="addScheme" class="offer">Add Scheme</a> <a href="removeScheme"
				class="offer">Remove Scheme</a> <a href="removeOfferByMerchant"
				class="offer">Remove Offer</a>
		</div>



		<!-- MIDDLE CONTAINER ends here  -->
		<form action="addScheme" method="post" onsubmit="return clickme()">
			<table>
				<tr>
					<td>Product Name</td>
					<td><select id="product_id" name="productId">
							<option>Please Select a Product</option>
							<c:forEach var="product" items="${list}">
								<option value="${product.productId}">
									<c:out value="${product.productName}"></c:out>
								</option>
							</c:forEach>
					</select></td>

				</tr>
				<tr>
					<td>Avalilable Scheme Id</td>
					<td><select id="scheme_id" name="schemeId">

					</select></td>
				</tr>
				<tr>
					<td>Start Time</td>
					<td><input name="startDate" type="text" id="datepicker"
						readonly="readonly" style="font-size: 20px" /> <!-- <input id="datepicker" type="text" name="startDate" value="">
	<script type="text/javascript">
		$(function(){
			$('*[name=startDate]').appendDtpicker();
		});
	</script> --></td>
				</tr>
				<tr>
					<td>End Time</td>
					<td><input name="endDate" type="text" id="datepicker1"
						readonly="readonly" style="font-size: 20px" /> <!-- <input id="datepicker1" type="text" name="endDate" value="">
	<script type="text/javascript">
		$(function(){
			$('*[name=endDate]').appendDtpicker();
		});
	</script> --></td>
				</tr>
				<tr>
					<td><input type="submit" value="Add Cupon"></td>


				</tr>
			</table>
		</form>
	</div>
	<!--Footer Section-->
	<div class="right-border"></div>
	<div class="footer">@Copyright Cap Store</div>
	<div class="footer-tile"></div>
	<!--Footer Section-->





</body>
</html>