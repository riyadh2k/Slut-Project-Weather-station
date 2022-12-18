<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.weatherBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>the weather</title>
<style>
			#regform{
			text-align:justify;
			padding: 15px 15px;
			background-color:#003366;
			height: 200px;
			
			border-radius:15px;
			}
			td{
			width:100px;
			color:white;
			}
			
			h1{
			margin:25px;
			text-align:center;
			padding:10px;
			background-color:orange;
			color:#fff;
			border:2px solid green;
			border-radius:10px;
			
			}
		</style>
</head>
<body>
<h1>{ Welcome to the Weather Station }</h1>
	
	<form action="OWservlet" method="get">
		<table id="regform" align="center">
		<tr><td>City:</td><td><input type="text" name="city" /></td></tr>
		<tr><td>Country:</td><td><input type="text" name="country" /></td></tr>
		<tr><td colspan=5 align="center"><input type="submit" value="go" /></td></tr></table>
	</form>

	<%
	weatherBean wBean = (weatherBean) request.getAttribute("wBean");
	out.print("The tempareture of " + wBean.getCityStr() + " is now  " + wBean.getTemperatureStr()+" C");
	out.print("<html><br></html>");
	out.print("The weather " + wBean.getCityStr() + " is now a " + wBean.getCloudsStr());
	%>

</body>
</html>