<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" http-equiv="Content-type" content="text/html">
<title>Find My Destiny!</title>
</head>
<body>

<%
String Visitor=request.getParameter("visitor");
Visitor = ((Visitor== null) || (Visitor.length() == 0)) ? "visitor" : Visitor; 
%>

<h1>Hello, <%= Visitor %>! This is Find My Destiny. Here, with our help, you can plan the travel of your dreams!</h1>

<p>The time now is <%= new java.util.Date() %></p>

</body>
</html>