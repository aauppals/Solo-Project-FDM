<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Hill Properties</title>
</head>
<body class="hero">
	<h1 style="color: white;">Please select a Hill Property below</h1>

<div class="container3">
	<form action="hillproperty" method="post">

		<select name="propertyName">

			<option value="" disabled selected>Choose option</option>
			
			<option value="Hyatt">Hyatt Hill Side</option>
			<option value="Ramada">Ramada Hill View</option>
			<option value="Novotel">Novotel Hill Side</option>

		</select>
	
		<br> <br> 
		<img src="resources/images/hyatt.jpg" alt="beach property" style= "float:left;width:250px; height:200px;">
        <img src="resources/images/novotel.jpg" alt="hill property" style= "float:right;width:250px; height:200px;">	
		
		<select name="propertyPrice">

			<option value="" disabled selected>Choose option</option>
			<option value="1900">1900 per night for Hyatt Hill Side</option>
			<option value="1200">1200 per night for Ramada Hill View</option>			
			<option value="1700">1700 per night for Novotel!</option>

		</select> <br><br><br><input type="submit" value="Submit selection">

	</form>
 <br><br> <br><br>
	<a href="index.jsp" style = "color:rgb(0, 0, 64);">Go to Home Page</a>
</div>
</body>
</html>