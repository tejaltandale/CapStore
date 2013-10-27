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
			<li><a href="addproducts">Add Products</a></li>
			<li><a href="findproducts">Show Inventory</a></li>
			<li><a href="updateproducts">Update Inventory</a></li>
			<li><a href="getAllMerchants">Merchant Details</a></li>
			<li><a href="getPendingMerchants">Add Merchants</a></li>
			<li><a href="getApprovedMerchants">Approved Merchants</a></li>
			<li><a href="getRemovedOrRejectedMerchants">Denied Merchants</a></li>
			<li><a href="inviteAMerchant">Invite Merchant</a></li>
			<li><a href="createOfferAdmin">Create Offers</a></li>

		</ul>
	</div>

	<div class="middle-container">
		<div class="buttons">

			<a href="createOfferAdmin" class="offer">Create Offer</a> <a
				href="removeOfferByAdmin" class="offer">Remove Offer</a>

		</div>


		<c:set var="is_created" value="${result}"></c:set>
		<c:if test="${is_created==true}">
			<script>
				alert("selected Offer successfully removed");
			</script>
		</c:if>
		<form action="removeOfferByAdmin" method="post"
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
					<th><button type="submit" value="delete">Remove Offer</button>
					</th>
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