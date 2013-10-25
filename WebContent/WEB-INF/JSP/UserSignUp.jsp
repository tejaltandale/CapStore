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
	if((document.getElementById("n1").value)=="" || (document.getElementById("n2").value)=="" || (document.getElementById("n3").value)=="" || (document.getElementById("n4").value=="") || (document.getElementById("n5").value)=="" || (document.getElementById("n7").value)=="" || (document.getElementById("n8").value)==""){
		alert("Fields should not be empty");
		return false;
	}
}
function isProperUserId(tmp){
	var userid=document.getElementById("n1").value;
	if (userid != "") {
		var regex = /^([C][_][0-9A-Za-z]+)$/;
		var kin = tmp.value;
		if (!regex.test(kin)) {
			alert("UserId must be in correct format");
			tmp.focus(); 
			return false;
		}
	}
}
function isProperFirstName(tmp){
	if ((document.getElementById("n2").value) != "") {
		var regex = /^([A-Za-z. ]+)$/;
		var kin = tmp.value;
		if (!regex.test(kin)) {
			alert("First Name must be in correct format(Ex-Ram)");
			tmp.focus(); 
			return false;
		}
	}
}
function isProperLastName(tmp){
if ((document.getElementById("n3").value) != "") {
	var regex = /^([A-Za-z. ]+)$/;
	var kin = tmp.value;
	if (!regex.test(kin)) {
		alert("Last Name must be in correct format(Ex-Ram)");
		tmp.focus(); 
		return false;
	}
}
}
function isProperPassword(tmp){
	if ((document.getElementById("n4").value) != "") {
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
	if ((document.getElementById("n5").value) != "") {
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
      
      

      
    
<!--Middle container starts Here---> 

<div class="middle-container">
<form action="AddUser">
<table>
<tr><td>UserId(Ex: C_XXXX)</td><td><input type=text id="n1" name="n1" onblur="isProperUserId(this);"></input></td></tr>

<tr><td>First Name</td><td><input type=text id="n2" name="n2" onblur="isProperFirstName(this);"></input></td></tr>

<tr><td>Last Name</td><td><input type=text id="n3" name="n3" onblur="isProperLastName(this);"></input></td></tr>

<tr><td>Password</td><td><input type=password id="n4" name="n4" onblur="isProperPassword(this);"></input></td></tr>

<tr><td>Email</td><td><input type=text id="n5" name="n5" onblur="isProperEmail(this);"></input></td></tr>

<tr><td>Security Question</td><td><select id="n6" name="n6"><option>What is your school name?</option><option>What is your nickname?</option><option>What is your mother's maiden name?</option><option>What is the name of the first company you worked for?</option></select></td></tr>

<tr><td>Answer</td><td><input type=text id="n7" name="n7" onblur="isProperAnswer(this);"></input></td></tr>

<tr><td>Address</td><td><textarea id="n8" name="n8" rows=10 cols=30></textarea></td></tr>

<tr><td></td><td><input type="submit" value="Submit" onclick="return areFieldsBlank()"></input><input type="reset" value="Cancel"></input></td></tr>
<tr><td></td><td>An activation link will be sent to your email on clicking submit.Click on it within 24 hrs to activate your account</td></tr>
</table>

</form>
</div> 

<!--Middle container ends Here--->
<!--Footer Section-->
<div class="right-border"></div>
<div class="footer">@Copyright Cap Store</div>
<div class="footer-tile"></div>
<!--Footer Section-->
</body>
</html>

