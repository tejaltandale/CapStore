<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cap Store </title>
<link type="text/css" href="resources/Css/style.css" rel="stylesheet"  />
<link type="text/css" href="resources/Css/login.css" rel="stylesheet"  />
<!-- <script src="resources/Script/SearchScript.js"></script> -->
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
    <script src="resources/Script/login.js"></script>
    <script src="resources/Script/scripts.js"></script>
     <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script> 
     <script src="resources/Script/jquery-2.0.3.min.js"></script>   
  <script src="resources/Script/jquery.slides.min.js"></script>
    <link rel="stylesheet" href="resources/Css/example.css">
  <link rel="stylesheet" href="resources/Css/font-awesome.min.css">
  <link rel="stylesheet" href="resources/Css/Slider.css">
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
<!-- <form action="tsearch" method="get">
		<input type="text" class="mainSearchBar" list="awards"
			style="color: #000" title="query" type="text" id="query" name="query"
			onblur="showText()" onfocus="hideText();" value=""
			placeholder="       Search Here">
			<div id="InitializeSearch"></div> <input class="mainSubmit" value=" "
			style="position: relative; margin-left: -76px;">
	</form> -->
<form action="tsearch" method="get">
<input  type="text" class="mainSearchBar" list="awards"  
style="color: #000" title="query" type="text" id="query" name="query" onblur="showText()" 
onfocus="hideText();" value="" placeholder="       Search Here" >
<div id="InitializeSearch"></div> 
<input class="mainSubmit" value=" " id="searchbutton" type="button" style="position:relative;margin-left:-76px;">
</form>
<!--Search Bar Ends Here-->      
</div>

  <!-- Login Starts Here -->
            <div id="loginContainer">
                <a href="#" id="loginButton"><span>Login</span><em></em></a>
                <div style="clear:both"></div>
                <div id="loginBox">                
                    <form id="loginForm" action="login" method="post" >
                        <fieldset id="body">
                            <fieldset>         
                                <label for="email">Email Address</label>
                                <input type="text" name="email" id="email" onblur="showText()"/>
                            </fieldset>
                            <fieldset>
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" onblur="showText()"  />
                            </fieldset>
                            
                            <input type="submit" id="login" value="Sign in" onclick="return areFieldsBlank()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="AskUserOrMerchant">Sign Up</a>
                            
                            </fieldset>
                        <span><a href="ChangePwd">Change password</a><a href="ForgotPwd">Forgot your password?</a></span>
                    </form>
                </div>
            </div>
            <!-- Login Ends Here -->
            ${username}
             <!-- Logout Ends Here -->
             <a href="#" id="logoutButton"><span>Logout</span><em></em></a>
			 <!-- Login Ends Here -->
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
	<!--Menu Section-->
	<div class="middle-container">
	<form name="wish" action="wishlist">
<input type="submit" class="headerbuttons" value="WishList"></input>
  </form>
       <div class="container">
    <div id="slides">
     <c:forEach items="${list2}" var="id">


<img src='<c:url value="${id}"></c:url>' width="30%" height="20%" alt="image not found"/>


</c:forEach>
    </div>
  </div>
  </div>
     
   
 
<!--Footer Section-->
<div class="right-border"></div>
<div class="footer">@Copyright Cap Store</div>
<div class="footer-tile"></div>
<!--Footer Section-->
</body>
</html>
