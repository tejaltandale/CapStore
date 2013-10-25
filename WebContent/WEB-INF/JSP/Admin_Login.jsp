<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script class="cssdeck" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<link type="text/css" href="resources/Css/Admin_Login.css" rel="stylesheet"  />
<!-- <link type="text/css" href="resources/Css/style.css" rel="stylesheet"  /> -->
<script src="resources/Script/login.js"></script>
<script src="http://s.cssdeck.com/assets/js/prefixfree.min.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<title>AdminLogin</title>
</head>
<body>
<div class="header">
		<div class="header-title">
			<img src="resources/Images/fly.png" border="0" class="fly"
				height="3px" width="30px" /> <img src="resources/Images/fly.png"
				border="0" class="fly1" height="3px" width="30px" /> <img
				src="resources/Images/fly.png" border="0" class="fly2" height="3px"
				width="30px" />
			<div class="wait"></div>
			Cap Store
		</div>
		</div>
<div class='login'>
<form name="form" method="post" action="admin_home" >
  <h2>Sign in</h2>
  <input name='username' id="username" placeholder='Username' type='text'/>
  <input id='pw' name='password' placeholder='Password' type="password"/>
  <input type='submit' value='Sign in' onclick="return validation()"/>
</form>
</div>

</body>

</html>