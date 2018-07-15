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
            <th>Name</th>
            <th>Email</th>
            <th>Age</th>
            <th>Register Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
        </tr>
        <tr>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
        </tr>
        <tr>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
            <td>Information</td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
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
