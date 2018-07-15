<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../fragments/header.jsp"/>
<div class="container">
    <a class="btn btn-primary" onclick="add()">

        Добавить

    </a>
    <%--tr - row--%>
    <%--th - заголовочная ячейка--%>
    <%--td - Обычная ячейка--%>
    <table id="userDataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Age</th>
            <th>Register Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${userList}" var="user">
            <jsp:useBean id="user" type="entity.User"/>
            <tr>
                <th><c:out value="${user.id}"/></th>
                <th><c:out value="${user.name}"/></th>
                <th><c:out value="${user.email}"/></th>
                <th><c:out value="${user.age}"/></th>
                <th><c:out value="${user.createdDate}"/></th>

                <th><a class="Edit" href="user?id=<c:out value='${user.id}'/>">Edit</a></th>

                <th><a class="Delete" href="user?id=<c:out value='${user.id}'/>">Delete</a></th>
            </tr>
        </c:forEach>
        </tbody>

        <tfoot>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Age</th>
            <th>Register Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </tfoot>
    </table>
</div>
<jsp:include page="../fragments/footer.jsp"/>
