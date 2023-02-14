<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>Travel Details</title>
</head>
<body class="hero">
	<h1 style="color: white;">Your travel details are below</h1>
	<div class="container2">
	<div style="background-color: #B0C4DE">
		<table border="1" width="400" align="center">
		
			<th>Conveyance Name</th>
			<th>Conveyance Price (HKD)</th>
			<tr>


				<td>${TravelInfo.getConvName()}</td>
				<td>${TravelInfo.getConvPrice()}</td>

			</tr>
		</table>
		</div>
		<br> <br> <a href="conveyanceregistry.jsp" style="color: rgb(0, 0, 64);">Re-select
			conveyance mode</a> <br> <br> <a href="locationregistry.jsp"
			style="color: rgb(0, 0, 64);">Re-select hotel</a> <br> <br>
		<a href="checkout" style = "color:rgb(0, 0, 64);">Go to checkout</a> <br> <br> 
		<a href="index.jsp" style = "color:rgb(0, 0, 64);">Go to home page</a>
		</div>
</body>
</html>


<!-- 	<br> <br> <a href="index.jsp">Home Page</a>
 -->