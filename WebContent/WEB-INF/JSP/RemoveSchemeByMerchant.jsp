<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remove Scheme</title>
<script src="resources/Script/jquery-2.0.3.min.js"></script>
<link type="text/css" href="resources/Css/login.css" rel="stylesheet">
<link type="text/css" href="resources/Css/style.css" rel="stylesheet">
<script src="resources/Script/login.js"></script>
<script src="resources/Script/scripts.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#product_id")
								.change(
										function() {
											var Parent = document
													.getElementById("table1");

											while (Parent.hasChildNodes()) {
												Parent
														.removeChild(Parent.firstChild);

											}

											var type1 = $("#product_id").val();

											$
													.ajax({
														type : "Get",
														url : "getAllSchemeByProductID",
														dataType : 'json',

														data : "productId="
																+ type1,
														success : function(data) {
															var row = $("<tr><td>Select Id\'s</td><td>Scheme Description</td></tr>");
															$("#table1")
																	.append(row);

															$
																	.each(
																			data,
																			function(
																					index,
																					value) {
																				var obj = JSON
																						.parse(value);
																				var row1 = $('<tr><td><input id="me" type="checkbox" name="selectedId" value="'+obj.schemeId+'"></td><td>'
																						+ obj.schemeDescription
																						+ '</td></tr>');
																				$(
																						"#table1")
																						.append(
																								row1);
																			});

														},
														error : function(e) {
															alert('Error: ' + e);
														}
													});

										});
					});
	function dinesh() {
		var selected_product = $("#product_id").val();
		if (selected_product == "Please Select a Product") {
			alert("please select a product");
			return false;
		}
		var no_of_selected_checkbox = $('input:checkbox[name="selectedId"]:checked').length;
		if (no_of_selected_checkbox == 0) {
			alert("please select atleast one check box");
			return false;
		}

	}
</script>
<body>
	<c:set var="is_created" value="${result}"></c:set>
	<c:if test="${is_created==true}">
		<script>
			alert("selected schemes successfully removed");
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


		<form action="removeScheme" method="post" onsubmit="return dinesh()">
			<table>
				<tr>
					<td>Product Id</td>
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
					<td colspan="2">
						<table border="2" align="center" id="table1">

						</table>
					</td>
				</tr>

				<tr>
					<td><input type="submit" value="Remove Scheme"></td>


				</tr>
			</table>
		</form>
	</div>
	<!-- Middle container ends here -->

	<!--Footer Section-->
	<div class="right-border"></div>
	<div class="footer">@Copyright Cap Store</div>
	<div class="footer-tile"></div>
	<!--Footer Section-->