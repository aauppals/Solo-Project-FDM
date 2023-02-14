<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Beach Properties</title>
</head>
<body class="hero">
	<h1 style="color: white;">Please select a Beach Property below</h1>


	<div class="container3">
		<form action="beachproperty" method="post">

			<select name="propertyName">

				<option value="" disabled selected>Select Hotel</option>

				<option value="Marriott">Marriott BeachSide Hotel</option>
				<option value="Sheraton">Sheraton Beachside Hotel</option>
				<option value="GoldCoast">Gold Coast Hotel</option>

			</select> <br> <br>
	<img src="resources/images/marriott.jpg" alt="beach property" style= "float:left;width:250px; height:200px;">
    <img src="resources/images/goldcoast.jpg" alt="hill property" style= "float:right;width:250px; height:200px;">		
			
			 <select name="propertyPrice">

				<option value="" disabled selected>Select price for hotel</option>
				<option value="1800">1800 per night for Marriott BeachSide</option>
				<option value="2200">2200 per night for Sheraton BeachSide</option>
				<option value="1400">1400 per night for Gold Coast Hotel</option>

			</select>  <br> <br><input type="submit" value="Submit selection">

		</form>


		<br> <br><br> <br><a href="index.jsp" style = "color:rgb(0, 0, 64);">Go to home Page</a>
	</div>
</body>
</html>