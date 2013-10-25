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
      
      
      
    
<!--Middle container starts Here--->

<div class="middle-container">
<br></br><br></br><br></br>
Your account cannot be created due to one of the following reasons:<br/>
1-The userid which you entered may already be existing.<br/>
2-The email which you entered may already be existing.
3-The email which you entered may not be valid.
<br/>
<a href="SignUpUser">Try again</a>&nbsp;&nbsp;&nbsp;<a href="Home">Home</a>
</div>

<!--Middle container ends Here--->
   <!---Clock Starts Here-->
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


