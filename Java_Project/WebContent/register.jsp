<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 --><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Register New Traveller</title>
</head>

<body class="hero">
<h1 style="color: white;">Please enter your details for registration</h1>
<div class="container2">
	<form action="registerUser" method="post">
		<label>First Name: </label><input type="text" name="fName"><br><br>
		<label>Last Name: </label><input type="text" name="lName"> <br><br>
		<label>User Name: </label><input type="text" name="uName"> <br><br>
		<label>Password: </label><input type="password" name="pwd"> <br><br>
		<label>Email: </label><input type="email" name="email"> <br><br>
		
		
		<input type="submit" value="Register">
	</form>
	</div>
</body>
</html>