<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script> -->
<title>Insert title here</title>
<link type="text/css" href="resources/Css/login.css" rel="stylesheet">
<link type="text/css" href="resources/Css/style.css" rel="stylesheet">
<script src="resources/Script/login.js"></script>
<script src="resources/Script/scripts.js"></script>
<script src="resources/Script/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	function dinesh() {
		var no_of_selected_checkbox = $('input:checkbox[name="selectedId"]:checked').length;
		if (no_of_selected_checkbox == 0) {
			alert("please select atleast one check box");
			return false;
		}

	}
</script>

</head>

<body>
	<div class="header-tile"></div>
	<div class="header">
		<div class="header-title">
			<img src="resources/Images/fly.png" border="0" class="fly"
				height="3px" width="30px" /> <img src="resources/Images/fly.png"
				border="0" class="fly1" height="3px" width="30px" /> <img
				src="resources/Images/fly.png" border="0" class="fly2" height="3px"
				width="30px" />Cap Store
		</div>
		<!--Header Section-->

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


	<c:set var="is_created" value="${result}"></c:set>
	<c:if test="${is_created==true}">
		<script>
				alert("selected Offer successfully removed");
			</script>
	</c:if>

	<div class="middle-container">
		<div class="buttons">

			<a href="createOffer" class="offer">Create Offer</a> <a
				href="addScheme" class="offer">Add Scheme</a> <a href="removeScheme"
				class="offer">Remove Scheme</a> <a href="removeOfferByMerchant"
				class="offer">Remove Offer</a>
		</div>

		<form action="removeOfferByMerchant" method="post"
			onsubmit="return dinesh()">
			<table align="center" border="2">
				<tr>
					<th>Scheme Id</th>
					<th>Offer Description</th>
				</tr>

				<c:forEach var="offer" items="${list}">
					<tr>
						<td><input id="me" type="checkbox" name="selectedId"
							value="${offer.schemeId}"></td>
						<td id="kin" align="center"><c:out
								value="${offer.schemeDescription}"></c:out></td>
					</tr>
				</c:forEach>
				<tr>
					<th><button type="submit" value="delete">Remove Offer</button></th>
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