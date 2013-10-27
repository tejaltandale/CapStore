<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Coupon</title>
<link type="text/css" href="resources/Css/login.css" rel="stylesheet">
<link type="text/css" href="resources/Css/style.css" rel="stylesheet">
<script src="resources/Script/login.js"></script>
<script src="resources/Script/scripts.js"></script>
<script src="resources/Script/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#cupon_type")
								.change(
										function() {
											var type = $("#cupon_type").val();
											if (type == "buyandget") {
												document
														.getElementById('product_id').style.visibility = "visible";
												document.getElementById('td1').style.visibility = "visible";
												$('#cupon_name').attr(
														'readonly', true);
												document
														.getElementById('product_id').options.length = 0;
												$
														.ajax({
															type : "Get",
															url : "getAllProductIdForMerchantID",
															dataType : 'json',
															data : "list" + '',
															success : function(
																	data) {
																$('#product_id')
																		.append(
																				$(
																						'<option></option>')
																						.html(
																								"please select a product Name"));
																$
																		.each(
																				data,
																				function(
																						index,
																						value) {
																					var obj = JSON
																							.parse(value);
																					$(
																							'#product_id')
																							.append(
																									$('<option value="'+obj.productId+'">'
																											+ obj.productName
																											+ '</option>'));
																				});

															},
															error : function(e) {

																alert('Error: '
																		+ e);
															}
														});
											} else {
												$('#cupon_name').attr(
														'readonly', false);
												$("#cupon_name").val('');
												document
														.getElementById('product_id').style.visibility = "hidden";
												document
														.getElementById('product_id').options.length = 0;
												document.getElementById('td1').style.visibility = "hidden";
											}
										});
					});
	$(document)
			.ready(
					function() {
						$("#submit")
								.click(
										function() {
											var type = $("#cupon_type").val();
											var name = $("#cupon_name").val();
											var value = $("#cupon_value").val();
											var product = $("#product_id")
													.val();
											var des = $("#des").val();
											if (type == "Please select a Offer Type") {
												alert("please select a Offer Type");
												return;
											}
											if (product == "please select a product Name") {
												alert("please select a product");
												return;
											}
											var pattern = new RegExp(
													"^[1-9][0-9]*$");
											if (type == "product") {
												var pattern1 = new RegExp(
														"^[A-Za-z]+[0-9]+$");
												if (!(name.match(pattern1))) {
													alert("please enter valid name");
													return;
												}
											}

											else if (type == "cart") {

												if (!(name.match(pattern))) {
													alert("please enter valid name");
													return;
												}
											}

											if (!(value.match(pattern))) {
												alert("please enter valid value");
												return;
											}
											if (des == "") {
												alert("please write valid description about offer");
												return;
											}

											$
													.ajax({
														type : "Post",
														url : "createOffer",
														dataType : 'json',
														data : "name="
																+ escape(name)
																+ "&type="
																+ type
																+ "&value="
																+ value
																+ "&des="
																+ escape(des),
														success : function(data) {
															alert(data);
															alert("successfully created");
															$("#create")[0]
																	.reset();

														},
														error : function(e) {

															alert('Error: ' + e);
														}
													});

										});
					});
	$(document).ready(function() {
		$("#product_id").change(function() {
			if ($("#product_id").val() == "please select a product Name") {
				$("#cupon_name").val('');

			} else {
				$("#cupon_name").val($("#product_id").val());
			}
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

		<form id="create">
			<table>
				<tr>
					<td>Offer Type</td>
					<td><select id="cupon_type" onchange="changeElement()"
						name="type">
							<!-- 		<option value="selecttype">Select Cupon Type</option> -->
							<option>Please select a Offer Type</option>
							<option value="cart">Cart</option>
							<option value="product">Product</option>
							<option value="buyandget">Buy And Get</option>

					</select></td>
					<td id="td1" style="visibility: hidden;">product id</td>
					<td><select id="product_id" name="product_id"
						style="visibility: hidden">
					</select></td>
				</tr>
				<tr>
					<td>Offer Name</td>
					<td id="one"><input type="text" id="cupon_name" name="name" />
					</td>

				</tr>

				<tr>
					<td>Offer Value</td>
					<td><input type="text" name="value" id="cupon_value" /></td>

				</tr>
				<tr>
					<td>Offer Description</td>
					<td><input type="text" name="des" id="des" /></td>
				</tr>
				<tr>
					<td><input id="submit" type="button" value="Create Cupon">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- MIDDLE CONTAINER ends here  -->

	<!--Footer Section-->
	<div class="right-border"></div>
	<div class="footer">@Copyright Cap Store</div>
	<div class="footer-tile"></div>
	<!--Footer Section-->
</body>
</html>