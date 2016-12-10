<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 
<%@page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page session="false" %>
<html>
	<head>
		<title>${message}</title>
	</head>
	<body>
	    <h1>${message}</h1>
		<f:form method="POST" action="addUser">
		<table>
			<tbody>
				<tr>
				  <td>User Id:</td>
				  <td><f:input path="id" size="30"></f:input></td>
				</tr>
				<tr>
				  <td>User Name:</td>
				  <td><f:input path="name" size="6"></f:input></td>
				</tr>
				<tr>
				  <td>User Password:</td>
				  <td><f:input path="password" size="20"></f:input></td>
				</tr>
				<tr>
        			<td colspan="2"><input type="submit" value="Add User"></td>
    			</tr>
			</tbody>
		</table>
		</f:form>	
	</body>
</html>