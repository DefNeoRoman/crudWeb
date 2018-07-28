
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
<div class="container">

    <a class="btn btn-info" id="userTask" href="task/user">
        user task
    </a>

    <a class="btn btn-info" id="adminTask" href="task/admin">
        admin task
    </a>
    <a class="btn btn-primary" id="logout" href="logout">
       logout
    </a>
</div>


