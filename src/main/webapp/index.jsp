<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Student</title>
</head>
<body>
    <h2>List Student</h2>
    <a href="create.jsp">Add New Student</a>
    
    <table border="1"> 
    	<tr>
    		<th>ID</th>
    		<th>Name</th>
    		<th>Email</th>
    		<th>Phone</th>
    	</tr>
	   	<s:iterator value="listStudents" var="e">
		   	<tr>
		   		<td>${e.id}</td>
		   		<td>${e.name}</td>
		   		<td>${e.email}</td>
		   		<td>${e.phone}</td>
		   	 </tr>
	    </s:iterator>
    </table>

</body>


</html>