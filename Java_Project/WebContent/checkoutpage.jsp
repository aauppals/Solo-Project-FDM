<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/style.css" rel="stylesheet">
<title>All details</title>
</head>
<body class="hero">
	<h1 style="color: white;">Almost there! Please check your details below</h1>
	<div class="container5">
	<div style="background-color: #B0C4DE">
	
	<table border="1" width="510" align="center">
		<tr bgcolor = "00FF75">
		<tr>
			<td><b>Hotel Details </b></td>
			<td>${info.ConvReg.useraccount.fName},${info.ConvReg.useraccount.lName} </td>
			<td>${info.LocReg.locationType}</td>
			<td>${info.LocReg.stayDuration} days</td>
		</tr>
		<tr>
		    <td><b>Hotel Pricing </b></td>
			<td>${info.LocDB.propertyName}</td>
			<td>${info.LocDB.propertyPrice} HKD per night</td>
			<td>Total stay price (HKD): ${info.LocReg.stayDuration}*${info.LocDB.propertyPrice} </td>
		</tr>
		<tr>
			<td><b>Travel Details</b> </td>
			<td>${info.ConvReg.useraccount.fName},${info.ConvReg.useraccount.lName} </td>
			<td>${info.ConvReg.convType}</td>
			<td>1 ticket</td>
		</tr>
		<tr>
			<td><b>Travel Pricing </b></td>
			<td>${info.ConvDB.convName}</td>
			<td>${info.ConvDB.convPrice}</td>
			<td>Total travel price(HKD): ${info.ConvDB.convPrice}</td>
		</tr>
	</table>
   </div>


	<br>
	<a href="payment.jsp" style="color: rgb(0, 0, 64);">Go to payment portal</a>
	<br><br>
	<a href="index.jsp" style="color: rgb(0, 0, 64);">Go back to home page</a>
	
	</div>
</body>
</html>


