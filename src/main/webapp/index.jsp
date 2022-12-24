<%@page import="controller.OWservlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.weatherBean"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>OpenWeather</title>
<style>
#regform {
	text-align: justify;
	padding: 15px 15px;
	background-color: #003366;
	height: 200px;
	border-radius: 15px;
}

td {
	width: 100px;
	color: white;
}

h1 {
	margin: 25px;
	text-align: center;
	padding: 10px;
	background-color: orange;
	color: #fff;
	border: 2px solid green;
	border-radius: 10px;
}
</style>
</head>
<body>
<%

%>


<%
// If checker is false show the form, otherwise don't
if(request.getAttribute("useCookie")== null){
	
	%>
	<div class="cookiePopup">
	 <form target="index.jsp">
    <h2>Do you accept cookies?</h2>
		<input type="submit" name="buttonResult" value="Accept" id="inputAccept"  />
		<input type="submit" name="buttonResult" value="Refuse" id="inputRefuse"  />
	</form>
	</div>
	<%
}
%>
	<h1>{ Welcome to the Weather Station }</h1>

	<form action="OWservlet" method="get">
		<table id="regform" align="center">
			<tr>
				<td>City:</td>
				<td><input type="text" name="city" /></td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><input type="text" name="country" /></td>
			</tr>
			<tr>
				<td colspan=5 align="center"><input type="submit" value="go" /></td>
			</tr>
		</table>
	</form>

</body>
</html>