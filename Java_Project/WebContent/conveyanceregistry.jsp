<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Conveyance Registry</title>
</head>
<body class="hero">
	<h1 style="color: white;">Please make appropriate selections below for your travel:</h1>

<div class="container2">
	<form action="convReg" method="post">

		<select name="convType">

			<option value="" disabled selected>Choose option</option>
			<option value="Flight">Flight (depends on availability)</option>
			<option value="Train">Train and bus package(depends on availability)</option>

		</select> <br><br><input type="submit" value="Submit selection">

	</form>

	<br><br><br><br><a href="index.jsp" style = "color:rgb(0, 0, 64);">Go to Home Page</a>
</div>
</body>
</html>