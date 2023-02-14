<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>propertyselection</title>
</head>
<body>
	<h1>Please make appropriate selections below for your stay</h1>

	<form action="locationDB" method="post">

		<select name="propertyName">

			<option value="" disabled selected>Choose option</option>
			<option value="Mariott">Marriott Hotel</option>
			<option value="Sheraton">Sheraton Hotel</option>
		 	<option value="Novotel">Novotel Hotel</option>
		</select>
		<!-- <input type="submit" name="locationType"> -->

		<br> <br> <select name="propertyPromotions">

			<option value="" disabled selected>Choose option</option>
			<option value="3">3 days</option>
			<option value="5">5 days</option>

		</select> <input type="submit" value="Submit selection">

	</form>

	<a href="index.jsp" style = "color:rgb(0, 0, 64);">Home Page</a>

</body>
</html>