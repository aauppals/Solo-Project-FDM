<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Train and bus</title>
</head>
<body class="hero">
<body>
	<h1 style="color: white;">Please select your preffered option below:</h1>
	<div class="container3">
	<br> <br>

<img src="resources/images/train.jpg" alt="beach property" style= "float:left;width:250px; height:200px;">
    <img src="resources/images/bus.jpg" alt="hill property" style= "float:right;width:250px; height:200px;">
	<form action="trainInfo" method="post">

		<select name="trainName">

			<option value="" disabled selected>Select conveyance option</option>

			<option value="Premium">Premium route : Train</option>
			<option value="Standard">Standard route : Train + bus </option>
			<option value="Budget">Budget route : Bus </option>

		</select> <br> <br> 
	
		
		<select name="trainPrice">

			<option value="" disabled selected>Conveyance prices</option>
			<option value="850">850 for Premium option</option>
			<option value="600">600 for Standard option</option>			
			<option value="350">350 for Budget option</option>

		</select><br> <br> <input type="submit" value="Submit selection">

	</form>

	<br> <br><br> <br><a href="index.jsp" style = "color:rgb(0, 0, 64);">Home Page</a>
</div>
</body>
</html>