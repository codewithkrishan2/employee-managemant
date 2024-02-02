<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kksg.controller.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Success</title>
</head>
<body>
	<h1>Success</h1>
	<%
	User u = (User) request.getAttribute("u");
	out.println(u.getUsername());
	%>

	<h2>
		<a href="display">Show All Employees</a>
	</h2>
	<h2>
		<a href="add">Add an Employee</a>
	</h2>



</body>
</html>