<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		   private int id;
    private String name;
    private String branch;
    private int percentage;
    private int phone;
    private String email;
	 -->
	<h2>Add New Student To Database</h2>
   <!-- <s:form action="CreateStudent" method="post" enctype="multipart/form-data">
   		<s:textfield label="Name" name="name"></s:textfield>
   		<s:textfield label="Branch" name="branch"></s:textfield>
   		<s:textfield label="Percentage" name="percentage"></s:textfield>
   		<s:textfield label="Phone" name="phone"></s:textfield>
   		<s:textfield label="Email" name="email"></s:textfield>
   		<s:submit value="Lưu vào database"></s:submit>
   </s:form> -->

   <form id="create-student-form">
		Name: <input type="text" name="name" id="name">
		Branch: <input type="text" name="branch" id="branch">
		Percentage: <input type="number" name="percentage" id="percentage">
		Phone: <input type="number" name="phone" id="phone">
		Email: <input type="email" name="email" id="email">
		<input type="submit" value="Lưu vào database">
   </form>

	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/student.js"></script>
</body>
</html>