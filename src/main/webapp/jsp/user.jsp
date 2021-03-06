<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../fragments/header.jsp"/>
<div class="container">

    <form method="get" action="user/create">
        <input type="hidden" name="currentPage" value="${currentPage}">
        <input type="hidden" name="limit" value="${limit}">
        <input class="btn btn-primary" type="submit" value="Add">
    </form>
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
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody id="arrayTable">

        <c:forEach items="${userList}" var="user">
            <jsp:useBean id="user" class="model.User"/>
            <tr id = "${user.id}">
                <td name="id"><c:out value="${user.id}"/></td>
                <td name="name"><c:out value="${user.name}"/></td>
                <td name="email"><c:out value="${user.email}"/></td>
                <td name="age"><c:out value="${user.age}"/></td>
                <td name="createdDate"><c:out value="${user.createdDate}"/></td>
                <td name="role"><c:out value="${user.role}"/></td>
                <td name="id">
                    <form method="get" action="user/edit">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input type="hidden" name="currentPage" value="${currentPage}">
                        <input type="hidden" name="limit" value="${limit}">
                        <input class="btn btn-primary" type="submit" value="Edit">
                    </form>
                </td>
                <td name="id">
                    <form method="post" action="user/delete?userId=${user.id}">
                        <input type="hidden" name="currentPage" value="${currentPage}">
                        <input type="hidden" name="limit" value="${limit}">
                        <input class="btn btn-primary" type="submit" value="Delete">
                    </form>
                </td>
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
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </tfoot>
    </table>
    <a class="btn btn-primary" id="homeButton" href="user">
        home
    </a>
    <a class="btn btn-primary" id="prevButton" href="user?action=getLimit&currentPage=${prev}&limit=${limit}">
        prev
    </a>
   <span class="btn btn-info" id="currentPage"><c:out value="${currentPage}"/></span>

    <a class="btn btn-primary" id="nextButton" href="user?action=getLimit&currentPage=${next}&limit=${limit}">
        next
    </a>
    <a class="btn btn-primary" id="endButton" href="user?action=getLimit&currentPage=lastPage&limit=${limit}">
        end
    </a>
</div>
<jsp:include page="../fragments/footer.jsp"/>
