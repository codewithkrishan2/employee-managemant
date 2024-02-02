<%@page import="com.kksg.controller.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Display All Employee</title>
<style>
table {
	border: 1px solid black;
}

td {
	border: 1px solid black;
}
</style>
</head>

<body>
	<h1>Displaying All Employee</h1>
	<table>
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Email</td>
			<td>Action</td>
		</tr>

		<%
		ArrayList<Employee> list = (ArrayList<Employee>) request.getAttribute("list");
		if (list != null) {
			for (Employee e : list) {
				out.print("<tr><td>" + e.getId());
				out.print("</td><td>" + e.getName());
				out.print("</td><td>" + e.getEmail());
				out.print("</td><td> <a href='delete?id=" + e.getId() + "'>Delete</a> || <a href='update?id=" + e.getId()
				+ "'>Update</a> </td></tr>");
			}
		} else {
			out.print("The list is null");
		}
		%>

	</table>

</body>

</html>