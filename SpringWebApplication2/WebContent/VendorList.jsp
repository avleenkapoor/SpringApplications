<%@page import="com.tadigital.entity.Vendor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%ArrayList<Vendor>list=(ArrayList<Vendor>)session.getAttribute("VENDORLIST");
for(Vendor vendor:list){
%>
<%=vendor.getName() %>
<%=vendor.getEmail() %>
<br>
<%} %>
