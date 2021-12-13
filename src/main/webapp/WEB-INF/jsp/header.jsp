<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <%-- js --%>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        const baseURL = <c:url value="/"/>;
    </script>
</head>
<body>
    Đây là header
    <c:choose>
        <c:when test="${sessionScope.loggedIn != null && sessionScope.loggedIn == true}">
            <p>Xin chào ${sessionScope.username}</p>
            <a href="./admin/">Trang quản lý</a>
            <a href="./logout">Logout</a>
        </c:when>
        <c:otherwise>
            <a href="./login">Login</a>
            <a href="./register">Register</a>
        </c:otherwise>
    </c:choose>
    <hr>



    <!-- Đừng thêm gì dưới đoạn này -->
    <main role="main" class="container">

