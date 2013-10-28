<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin</title>
<link type="text/css" href="resources/Css/style.css" rel="stylesheet"  />
<link type="text/css" href="resources/Css/login.css" rel="stylesheet"  />
<link type="text/css" href="resources/Css/table.css" rel="stylesheet"  />

 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
    <script src="resources/Script/login.js"></script>
    <script src="resources/Script/scripts.js"></script>
    <script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/Script/chartify.js"></script>
<script type="text/javascript" src="resources/Script/Report.js"></script>
</head>


<body oncontextmenu="return false;">
<!--Header Section-->

<div class="header-tile"></div>
<div class="header" >
<div class="header-title">
<img src="resources/Images/fly.png" border="0" class="fly" height="3px" width="30px"  />
<img src="resources/Images/fly.png" border="0" class="fly1" height="3px" width="30px"/>
<img src="resources/Images/fly.png" border="0" class="fly2" height="3px" width="30px"/>Cap Store</div>
<!--Header Section->
<!--Search Bar Starts Here-->
<form action="tsearch" method="get">
<input  type="text" class="mainSearchBar" list="awards"  style="color: #000" title="Search" type="text" name="Search" onblur="showText()" onfocus="hideText();" value="" placeholder="       Search Here" >

<datalist id="awards" list="awards" class="datalist1">
<select>
<option value="Best Picture"></option>
<option value="Best Director"></option>
<option value="Best Adapted Screenplay"></option>
<option value="Best Original Screenplay"></option>
</select>
</datalist>

<input class="mainSubmit" value=" " type="submit" style="position:relative;margin-left:-76px;">
</form>
<!--Search Bar Ends Here-->      
</div>
 <!-- Logout Starts Here -->
	<a href="logout" id="logout-button"><span>Logout</span><em></em></a>
	<!-- Logout Ends Here -->
	

<div class="left-border"></div>


<!--Header Section Ends Here-->


<!--Menu Section-->
	<div id='cssmenu'>
<ul>
   <li class='active'><a href='home.jsp'><span>Home</span></a></li>
   <li><a href='#'><span>Mobiles @ Accessories</span></a></li>
   <li><a href='#'><span>Clothing</span></a></li>
    <li><a href='#'><span>Footwears</span></a></li>
     <li><a href='#'><span>Cosmetics</span></a></li>
   <li class='last'><a href='#'><span>Computers & Accessories</span></a></li>
</ul>
</div>         
   <!--navside-bar starts here  --> 
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
    <!--navside-bar ends here  -->  
      
      
      
      
      <!---Clock Starts Here-->
<div class="time">
<iframe class="gwt-Frame" id="remote_iframe_3" name="remote_iframe_3" frameborder="0" scrolling="no" src="http://www-ig-opensocial.googleusercontent.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.gstatic.com%2Fig%2Fmodules%2Fdatetime_v3%2Fdatetime_v3.xml&amp;container=ig&amp;view=home&amp;lang=en&amp;country=US&amp;sanitize=0&amp;v=a436ac81fcfa0869&amp;parent=http://www.google.com&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;synd=ig&amp;mid=3#rpctoken=-874821815&amp;ifpctok=-874821815&amp;up_mainClock=&amp;up_mainClockDSTOffset=&amp;up_mainClockTimeZoneOffset=&amp;up_dateFormat=wmd&amp;up_useServerTime=false&amp;up_firstDay=0&amp;up_clocks=%5B%5D&amp;up_color=grey&amp;up_showWorldClocks=true&amp;up_24hourClock=true" style="height: auto;"></iframe>
</div>
 <!--Clock Ends Here--->
 
 <div class="middle-container">
 
 
	<strong><h1>
			<em>Statistics</em>
		</h1></strong>
	<h3>Merchant Share (Transactions)</h3>
	<table id="my-table">
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list4}" var="id1">
					<th scope="col">${id1.merchantname}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Amount</th>
				<c:forEach items="${list4}" var="id1">
					<td>${id1.numberoftransactions}</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<br>
	<br>
	<br>
	<h3>Product Views</h3>
	<table class="product-view">
		<caption>Products and their views</caption>
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list2}" var="id1">
					<th scope="col">${id1.productName}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Views</th>
				<c:forEach items="${list2}" var="id1">
					<td>${id1.views}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>
	<br>
	<br>
	<br>
	<h3>Most Wishlisted Items</h3>
	<table class="most-wishlisted">
		<caption>Most Wishlisted Items</caption>
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list1}" var="id1">
					<th scope="col">${id1.productName}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Wishlist count</th>
				<c:forEach items="${list1}" var="id1">
					<td>${id1.count}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>
	<br>
	<br>
	<br>
	<h3>Most Bought Products</h3>
	<table class="mostpopular-products">
		<caption>Most Popular Products</caption>
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list3}" var="id1">
					<th scope="col">${id1.productname}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Most popular products</th>
				<c:forEach items="${list3}" var="id1">
					<td>${id1.numberofsales}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>
	<br>
	<br>
	<br>

	<h3>Merchant Share</h3>
	<table class="merchant-selling">
		<caption>Merchant Share</caption>
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list11}" var="id1">
					<th scope="col">${id1.merchantname}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Merchant Share</th>
				<c:forEach items="${list11}" var="id1">
					<td>${id1.revenue}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>

	<br>
	<br>
	<br>
	<h3>Top Rated Products</h3>
	<div class="CSSTableGenerator" style="width: 400px; height: 110px;">
		<table>
			<tr>
				<td>Product Name</td>
				<td>Category</td>

			</tr>

			<c:forEach items="${list7}" var="id1">
				<tr>
					<td>${id1.productName}</td>
					<td>${id1.category}</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<br>
	<br>
	<br>
	<h3>Top Rated Merchants</h3>
	<div class="CSSTableGenerator" style="width: 400px; height: 110px;">
		<table>
			<tr>
				<td>Merchant ID</td>
				<td>Merchant Name</td>

			</tr>

			<c:forEach items="${list6}" var="id1">
				<tr>
					<td>${id1.merchantid}</td>
					<td>${id1.merchantName}</td>
				</tr>
			</c:forEach>

		</table>
	</div>


	<br>
	<br>
	<br>

	<h3>Top 5 most sold Products</h3>
	<table class="best-selling">
		<caption>Top 5 most sold Products</caption>
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list8}" var="id1">
					<th scope="col">${id1.productName}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Top 5 most sold Products</th>
				<c:forEach items="${list8}" var="id1">
					<td>${id1.numberoftransactions}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>
	<br>
	<br>
	<br>

	<h3>Revenue</h3>
	<table class="revenue">
		<caption>revenue</caption>
		<thead>
			<tr>
				<td></td>
				<th>First Quarter</th>
				<th>Second Quarter</th>
				<th>Third Quarter</th>
				<th>Fourth Quarter</th>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Revenue</th>


				<c:forEach items="${list9}" var="id1">
					<td>${id1.firstrevenue}</td>
					<td>${id1.secondrevenue}</td>
					<td>${id1.thirdrevenue}</td>
					<td>${id1.fourthrevenue}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>
	<p>All figures are in rupees
	<p>
	<h3></h3>
	<br>
	<br>
	<br>
	<h3>Category wise distribution (Transactions)</h3>
	<table class="category-selling">
		<caption>Category wise distribution</caption>
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list10}" var="id1">
					<th scope="col">${id1.categoryname}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Category wise distribution</th>
				<c:forEach items="${list10}" var="id1">
					<td>${id1.categorycount}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>

	<br>
	<br>
	<br>
	<h3>Category wise distribution (Transactions)</h3>
	<table class="category-selling2">
		<caption>Category wise distribution</caption>
		<thead>
			<tr>
				<td></td>
				<c:forEach items="${list10}" var="id1">
					<th scope="col">${id1.categoryname}</th>
				</c:forEach>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Category wise distribution</th>
				<c:forEach items="${list10}" var="id1">
					<td>${id1.categorycount}</td>
				</c:forEach>

			</tr>

		</tbody>
	</table>
 </div>
<!--Footer Section-->
<div class="right-border"></div>
<div class="footer">@Copyright Cap Store</div>
<div class="footer-tile"></div>
<!--Footer Section-->
</body>
</html>