<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Shopping Cart - Shop</title>
<style type="text/css">
<!--
.container {
	height: 800px;
	width: 900px;
	margin: auto;
}

.headbanner {
	float: left;
	height: 60px;
	width: 900px;
	vertical-align: middle;
	background-color: #FFCC00;
}

.container .headbanner h1 {
	font-family: Verdana, Geneva, sans-serif;
	color: #CC3300;
}

.mycontent {
	float: left;
	height: 650px;
	width: 900px;
}

.myfooter {
	float: left;
	height: 100px;
	width: 900px;
	border-top-width: 3px;
	border-top-style: solid;
	border-top-color: #FFCC00;
}

.container .myfooter h3 {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 12px;
	color: #CC3300;
}

.cartof {
	float: left;
	height: 25px;
	width: 900px;
	font-family: Verdana, Geneva, sans-serif;
	font-weight: bold;
	padding-top: 5px;
}

.cartcontent {
	float: left;
	height: 180px;
	width: 900px;
	background-color: #FFFFC2;
	border-width: 1px;
	border-style: solid;
	border-color: #FFCC00;
}

.prod1 {
	float: left;
	height: 100px;
	width: 900px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #FFCC00;
}

td {
	padding-left: 5px;
	color: #900;
	font-family: Verdana, Geneva, sans-serif;
}

.items {
	float: left;
	height: auto;
	width: 900px;
}

tr.border_bottom td {
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #FFCC00;
	height: 100px;
}

.myitems {
	float: left;
	height: 180px;
	width: 600px;
	border-right-width: 1px;
	border-right-style: solid;
	border-right-color: #FFCC00;
	overflow: auto;
}

th {
	color: #FFF;
	font-family: Verdana, Geneva, sans-serif;
	font-weight: normal;
	background-color: #900;
}

.total {
	float: left;
	height: 180px;
	width: 285px;
	font-family: Verdana, Geneva, sans-serif;
	font-size: 24px;
	font-weight: bold;
	padding-top: 20px;
	padding-left: 10px;
}
-->

</style>
<script type="text/javascript">
function functionCart(){
var txt=${total};
	if(txt==0){
		alert("Cart is Empty!!!");
		return false;
	}
}
</script>
</head>
<body style="margin: 0; padding: 0">
	<div class="container" align="justify">
		
			<div class="headbanner" align="left">
				<h1 style="display: inline">
					<img src="resources/Images/shopping.png" />[Shopping Cart]
				</h1>
			</div>
			<div class="mycontent">
				<div class="cartcontent">
				<form action="DisplayCart" method="GET" target="_self">
					<div class="myitems">
						<table width="600px" cellpadding="0" cellspacing="0">
							<tr>
								<th align="center">#id</th>
								<th align="center">Item</th>
								<th align="center">Price</th>
								<th align="center">Quantity</th>
								<th align="center">Action</th>
							</tr>
							<c:if test="${!empty itemlist}">
							 <c:set var="count" scope="page" value="0" />
								<c:forEach items="${itemlist}" var="list">
							<c:set var="count" scope="page" value="${count + 1}" />
							<tr>
							<td align="center">
							<c:out value="${count}"></c:out>
							</td>
							<td align="center">
							<c:out value="${list.product_name}"></c:out>
							</td>
							<td align="center">
							<c:out value="${list.actual_price}"></c:out>
							</td>
							<td align="center">
							<c:out value="${list.qty}"></c:out>
							</td>
							<td align="center"><input name="del" type="submit"
									value="Delete" onclick="this.value=${count-1}"></input></td>
							</tr>
							</c:forEach>
							</c:if>
						</table>
					</div>
					</form>
					<form action="DisplayCart" method="get" target="page" onsubmit="return functionCart();">
					<div class="total" id = "total">
						<a>Total : Rs[${total}]
						</a><br /> <a style="font-size: 14px">Total Qty : [${qty}]
						</a><br /> <input name="chkout" type="submit" value="Checkout"/>
					</div>
					</form>
				</div>
			</div>
			<div class="myfooter">
				<h3 style="font-weight: normal; padding-left: 10px">&copy;CapStore</h3>
			</div>
	</div>
</body>
</html>