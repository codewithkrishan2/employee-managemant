<%@page import="com.kksg.controller.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update employee</title>
</head>
<body>
	<form action="doUpdate" method="post">		
		
		<%
		Employee emp = (Employee) request.getAttribute("employee");
		out.println("ID: <input type='text' name='id' readonly value='"+emp.getId()+"'><br><br>");
		out.println("NAME: <input type='text' name='name' value='"+emp.getName()+"'><br><br>");
		out.println("EMAIL: <input type='email' name='email' value='"+emp.getEmail()+"'><br><br>");
		out.println("<input type='submit' value='Submit'>");
		%>

	</form>
</body>
</html>