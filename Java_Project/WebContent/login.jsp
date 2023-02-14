<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Traveller Login</title>
</head>
<body class="hero">
	<h1 style="color: white;">Please enter your login details below</h1>
	<div class="container2">
		<form action="verifyUser" method="post">
			<label>User Name: </label><input type="text" name="uName"> <br>
			<br> <label>Password: </label><input type="text" name="pwd">
			<br><br>
			<input type="submit" value="Login" >
		</form>
	</div>
</body>
</html>