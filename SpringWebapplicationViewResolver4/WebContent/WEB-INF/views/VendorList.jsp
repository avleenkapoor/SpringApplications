<%@page import="com.tadigital.entity.Vendor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<table>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Email</th>
		<th></th>
		<th></th>
	</tr>
	<%
		List<Vendor> list = (List<Vendor>)session.getAttribute("VENDORLIST");
		for (Vendor vendor : list) {
	%>
	<tr>
		<td><%=vendor.getId()%></td>
		<td><%=vendor.getName()%></td>
		<td><%=vendor.getEmail()%></td>
		<td><a href="edit<%=vendor.getId()%>"> EDIT</a></td>
		<td><a href="delete<%=vendor.getId()%>">DELETE</a></td>
	</tr>
	<%
		}
	%>
</table>
