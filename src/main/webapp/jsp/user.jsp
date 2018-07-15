<%@ page import="entity.User" %>
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

            <jsp:useBean id="user" class="entity.User"/>
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.createdDate}"/></td>
                <td>
                    <a class="btn btn-primary" onclick="editUser(${user.id})">
                        Edit
                    </a>
                </td>
                <td>
                    <a class="btn btn-primary" onclick="deleteUser(${user.id})">
                        Delete
                    </a>
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
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </tfoot>
    </table>
</div>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
        <form class="form-horizontal" id="detailsForm" method="post" action="javascript:void(null);"
                      onsubmit="call(this)">
            <%
                User userById =(User) request.getAttribute("userById");
            %>


                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">User name</label>

                        <div class="col-xs-9">
                 <input type="text" class="form-control" id="name" name="name" value="
                 <%
                 userById.getName();
                 %>
">
                        </div>

                        <label for="email" class="control-label col-xs-3">User email</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" value="
                            <%
                            userById.getEmail();
                            %>
">
                        </div>

                        <label for="age" class="control-label col-xs-3">User age</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="age" name="age" value="
                            <%
                            userById.getAge();
                            %>
">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <input class="btn btn-primary" type="submit" value="save">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
