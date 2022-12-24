<%@page import="org.eclipse.jdt.internal.compiler.ast.IfStatement"%>
<%@page import="controller.OWservlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.weatherBean"%>
<!DOCTYPE html>
<html>
<head>

<style>
div {
	text-align: center;
	color: tomato;
}

img {
	width: 100px;
	height: 100px;
}

a {
	background-color: orange;
	padding: 5px;
	margin: 5px;
	border-radius: 10px;
}

script {
	align-content: center;
	color: black;
}
</style>
</head>
<jsp:include page="./index.jsp"></jsp:include>
<body>
	<div>
		<div>

			<%
			weatherBean wBean = (weatherBean) request.getAttribute("wBean");
			out.print("<h3>" + "The tempareture of " + wBean.getCityStr() + " is now  " + wBean.getTemperatureStr() + " °C"
					+ "</h3>");
			out.print("<h3>" + "The weather " + wBean.getCityStr() + " is now a " + wBean.getCloudsStr() + "</h3>");

			if (wBean.getCloudsStr().contains("cloud")) {
				out.print("<img src='./image/cloudy.png' alt='cloudy' />");
			} else if (wBean.getCloudsStr().contains("heavy")) {
				out.print("<img src='./image/heavyrain.png' alt='heavyrain' />");
			} else if (wBean.getCloudsStr().contains("light")) {
				out.print("<img src='./image/lightrain.png' alt='lightrain' />");
			} else if (wBean.getCloudsStr().contains("snow")) {
				out.print("<img src='./image/snow.png' alt='snow' />");
			} else if (wBean.getCloudsStr().contains("sunny")) {
				out.print("<img src='./image/sunny.png' alt='sunny' />");
			} else if (wBean.getCloudsStr().contains("thunder")) {
				out.print("<img src='./image/thunder.png' alt='thunder' />");
			}
			%>

		</div>
		<%
			weatherBean weatherBean = (weatherBean) request.getAttribute("wBean");
			for (int i = 0; i < 4; i++) {
				Cookie existing = OWservlet.GetCookie(request, "Cookie" + i);
				if (existing != null) {
			String[] parts = existing.getValue().split("\\|");

			out.append("<a href=" + parts[0] + ">" + parts[1] + "</a>");
				}

			}
		
		%>

	</div>
</body>
</html>