<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../fragments/header.jsp"/>
<div class="container">
    <h1>Login page</h1>
    <h2>login or register</h2>
    <div class="container">
        <form method="post" action="user/login">
            <input type="hidden" id="id" name="id" value="${user.id}">
            <div class="form-group">
                <label for="name" class="control-label col-xs-3">User name</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                </div>
                <label for="password" class="control-label col-xs-3">User password</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="password" name="password" value="${user.password}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input class="btn btn-primary" type="submit" value="login">
                    <input class="btn btn-dark" type="submit" value="register">
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
