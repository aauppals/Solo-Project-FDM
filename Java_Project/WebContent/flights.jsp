<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Flights</title>
</head>
<body class="hero">
	<h1 style="color: white;">Please select your flight below:</h1>

<div class="container3">
	<form action="flightInfo" method="post">

		<select name="flightName">

			<option value="" disabled selected>Select a flight</option>

			<option value="CathayPacific">Cathay pacific (Premium route)</option>
			<option value="DragonAir">Dragon Air (Standard route) </option>
			<option value="HKExpress">HK Express (Budget route)</option>

		</select> <br> <br> 
		
		<img src="resources/images/cathay.jpg" alt="cathay" style= "float:left;width:250px; height:200px;">
        <img src="resources/images/dragonair.png" alt="dragon air" style= "float:right;width:250px; height:200px;">	
		
		<select name="flightPrice">
		
			<option value="" disabled selected>Flight prices</option>
			<option value="1900">1900 for Premium flight</option>
			<option value="1500">1500 for Standard flight</option>			
			<option value="900">900 for Budget flight</option>

		</select><br> <br><input type="submit" value="Submit selection" >

	</form>

	 <br> <br> <br> <br><a href="index.jsp" style = "color:rgb(0, 0, 64)">Go to home Page</a>
</div>
</body>
</html>