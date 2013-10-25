<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link type="text/css" href="resources/Css/style.css" rel="stylesheet"  />
<link type="text/css" href="resources/Css/login.css" rel="stylesheet"  />

 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
    <script src="resources/Script/login.js"></script>
    <script src="resources/Script/scripts.js"></script>
<script type="text/javascript">

function areFieldsBlank(){
	if((document.getElementById("n1").value)=="" || (document.getElementById("n2").value)=="" || (document.getElementById("n3").value)=="" || (document.getElementById("n4").value=="") || (document.getElementById("n7").value)=="" || (document.getElementById("n8").value)==""){
		alert("Fields should not be empty");
		return false;
	}
}
function isProperMerchantId(tmp){
	var userid=document.getElementById("n1").value;
	if (userid != "") {
		var regex = /^([M][_][0-9A-Za-z]+)$/;
		var kin = tmp.value;
		if (!regex.test(kin)) {
			alert("MerchantId must be in correct format");
			tmp.focus(); 
			return false;
		}
	}
}
function isProperName(tmp){
	if ((document.getElementById("n2").value) != "") {
		var regex = /^([A-Za-z. ]+)$/;
		var kin = tmp.value;
		if (!regex.test(kin)) {
			alert("Merchant Name must be in correct format(Ex-Samsung)");
			tmp.focus(); 
			return false;
		}
	}
}

function isProperPassword(tmp){
	if ((document.getElementById("n3").value) != "") {
		var regex = /^([A-Za-z!0-9@#$%^&|]{8})$/;
		var kin = tmp.value;
		if (!regex.test(kin)) {
			alert("Password must be 8 characters and it should contain at least one special character(@,!,#)");
			tmp.focus(); 
			return false;
		}
	}
}
function isProperEmail(tmp){
	if ((document.getElementById("n4").value) != "") {
		var regex = /^([a-z]+[a-z0-9_.]+[@][a-z]+[.][a-z]{2,3})$/;
		var kin = tmp.value;
		if (!regex.test(kin)) {
			alert("Email Id must be in correct format(Ex-x.x@x.com)");
			tmp.focus();  
			return false;
		}
	}
}
function isProperAnswer(tmp){
	if ((document.getElementById("n7").value) != "") {
		var regex = /^([a-zA-Z. ]+)$/;
		var kin = tmp.value;
		if (!regex.test(kin)) {
			alert("Answer field should contain only characters");
			tmp.focus();  
			return false;
		}
	}
}

</script>
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
  <!-- Login Starts Here -->
            <div id="loginContainer">
                <a href="#" id="loginButton"><span>Login</span><em></em></a>
                <div style="clear:both"></div>
                <div id="loginBox">                
                    <form id="loginForm">
                        <fieldset id="body">
                            <fieldset>         
                                <label for="email">Email Address</label>
                                <input type="text" name="email" id="email" onblur="showText()"/>
                            </fieldset>
                            <fieldset>
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" onblur="showText()" />
                            </fieldset>
                            <input type="submit" id="login" value="Sign in" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="AskUserOrMerchant">Sign Up</a>
                            
                        </fieldset>
                        <span><a href="#">Change password</a><a href="#">Forgot your password?</a></span>
                    </form>
                </div>
            </div>
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

<div class="navbox-sidebar">
<ul class="nav-sidebar">
<li><a href="#">Menu-1</a></li>
<li><a href="#">Menu-2</a></li>
<li><a href="#">Menu-3</a></li>
</ul>
</div>
      
      
      
      <!---Clock Starts Here-->
<div class="time">
<iframe class="gwt-Frame" id="remote_iframe_3" name="remote_iframe_3" frameborder="0" scrolling="no" src="http://www-ig-opensocial.googleusercontent.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.gstatic.com%2Fig%2Fmodules%2Fdatetime_v3%2Fdatetime_v3.xml&amp;container=ig&amp;view=home&amp;lang=en&amp;country=US&amp;sanitize=0&amp;v=a436ac81fcfa0869&amp;parent=http://www.google.com&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;synd=ig&amp;mid=3#rpctoken=-874821815&amp;ifpctok=-874821815&amp;up_mainClock=&amp;up_mainClockDSTOffset=&amp;up_mainClockTimeZoneOffset=&amp;up_dateFormat=wmd&amp;up_useServerTime=false&amp;up_firstDay=0&amp;up_clocks=%5B%5D&amp;up_color=grey&amp;up_showWorldClocks=true&amp;up_24hourClock=true" style="height: auto;"></iframe>
</div>
 <!--Clock Ends Here--->
 
<!--Middle container starts Here--->

<div class="middle-container">

<form action="AddMerchant" method="post">
<table>
<tr><td>MerchantId(Ex: M_XXXX)</td><td><input type=text id="n1" name="n1" onblur="isProperMerchantId(this);"></input></td></tr>

<tr><td>Merchant Name</td><td><input type=text id="n2" name="n2" onblur="isProperName(this);"></input></td></tr>

<tr><td>Password</td><td><input type=password id="n3" name="n3" onblur="isProperPassword(this);"></input></td></tr>

<tr><td>Email</td><td><input type=text id="n4" name="n4" onblur="isProperEmail(this);"></input></td></tr>

<tr><td>Merchant Type</td><td><input type=text id="n5" name="n5" readonly="readonly" value="Third Party"></input></td></tr>

<tr><td>Security Question</td><td><select id="n6" name="n6"><option>What is the first alphabet of your company name?</option><option>Where is your headquarters located?</option><option>Who created your company?</option></select></td></tr>

<tr><td>Answer</td><td><input type=text id="n7" name="n7" onblur="isProperAnswer(this);"></input></td></tr>

<tr><td>Address</td><td><textarea id="n8" name="n8" rows=10 cols=30></textarea></td></tr>

<tr><td></td><td><input type="submit" value="Submit" onclick="return areFieldsBlank()"></input><input type="reset" value="Cancel"></input></td></tr>
<tr></tr>
<tr><td></td><td>An email will be sent to CapStore's Admin on clicking submit.Your account will be activated after Admin's permission within 2 hours.</td></tr>
</table>

</form>

</div> 

<!--Middle container ends Here--->
!---Clock Starts Here-->
<div class="time">
<iframe class="gwt-Frame" id="remote_iframe_3" name="remote_iframe_3" frameborder="0" scrolling="no" src="http://www-ig-opensocial.googleusercontent.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.gstatic.com%2Fig%2Fmodules%2Fdatetime_v3%2Fdatetime_v3.xml&amp;container=ig&amp;view=home&amp;lang=en&amp;country=US&amp;sanitize=0&amp;v=a436ac81fcfa0869&amp;parent=http://www.google.com&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;synd=ig&amp;mid=3#rpctoken=-874821815&amp;ifpctok=-874821815&amp;up_mainClock=&amp;up_mainClockDSTOffset=&amp;up_mainClockTimeZoneOffset=&amp;up_dateFormat=wmd&amp;up_useServerTime=false&amp;up_firstDay=0&amp;up_clocks=%5B%5D&amp;up_color=grey&amp;up_showWorldClocks=true&amp;up_24hourClock=true" style="height: auto;"></iframe>
</div>
 <!--Clock Ends Here--->
 
<!--Footer Section-->
<div class="right-border"></div>
<div class="footer">@Copyright Cap Store</div>
<div class="footer-tile"></div>
<!--Footer Section-->
</body>
</html>

