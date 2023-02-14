<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Location Registry</title>
</head>

<body class="hero">

	<h1 style="color: white;">Please make appropriate selections below for your stay</h1>
	
	
	<div class="container3">
		<form action="locReg" method="post">

			<select name="locationType">

				<option value="" disabled selected>Select a property type</option>
				<option value="BeachFront">Beach Front</option>
				<option value="HillSide">Hill Side</option>

			</select>
			<!-- <input type="submit" name="locationType"> -->
<img src="resources/images/beach_image.jpg" alt="beach property" style= "float:left;width:250px; height:200px;">
<img src="resources/images/hill_image.jpg" alt="hill property" style= "float:right;width:250px; height:200px;">
			<br> <br> <select name="stayDuration">

				<option value="" disabled selected>Select stay duration</option>
				<option value="3">3 days</option>
				<option value="5">5 days</option>

			</select> <br> <br>
			
			<input type="submit" value="Submit selection">

		</form>

		<br> <br><br> <br><a href="index.jsp" style = "color:rgb(0, 0, 64);">Go to home Page</a>
	</div>
</body>
</html>