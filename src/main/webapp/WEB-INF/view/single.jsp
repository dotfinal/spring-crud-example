<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Single User</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>

<div class="container">
    <div class="col-md-12">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger center-block" role="alert">${errorMessage}</div>
        </c:if>
        <form:form id="form" class="center-block" action="${user.id eq 0 ? 'addUser' : 'updateUser'}" method="POST"
                   commandName="user">
            <c:if test="${user.name != null}">
                <div class="form-group">
                    <label>ID</label>
                    <form:input path="id" class="form-control" type="text" value="${user.id}" name="id"
                                readonly="true"/>
                </div>
            </c:if>
            <div class="form-group">
                <label>Name</label>
                <form:input path="name" class="form-control" type="text" value="${user.name}"/>
            </div>
            <div class="form-group">
                <label>Age</label>
                <form:input path="age" class="form-control" type="text" value="${user.age}"/>
            </div>
            <div class="form-group">
                <label>Date of birth</label>
                <fmt:formatDate var="qwer" value='${user.createdDate}' pattern='dd.MM.yyyy'/>
                <form:input path="createdDate" class="form-control" type="text" value="${qwer}"/>
            </div>
            <div class="checkbox text-center">
                <label><form:checkbox path="admin" checked="${user.admin eq true ? checked : unchecked}"/>Admin</label>
            </div>
            <button type="submit"
                    class="btn btn-primary savebutton center-block"><span
                    class="glyphicon glyphicon-ok-sign"></span> Save
            </button>
        </form:form>
    </div>
</div>
</body>
</html>
