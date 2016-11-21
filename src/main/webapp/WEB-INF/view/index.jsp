<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Users CRUD</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div class="container">
    <div class="searchbox center-block">
        <form id="form" class="center-block" action="search" method="POST">
            <div class="input-group">
                <input type="text" class="form-control" name="query" placeholder="Search for...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Go</button>
                    </span>
            </div>
        </form>
    </div>
    <c:if test="${not empty message}">
        <c:choose>
            <c:when test="${isSuccess eq true}">
                <div class="alert alert-success center-block" role="alert">${message}</div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger center-block" role="alert">${message}</div>
            </c:otherwise>
        </c:choose>
    </c:if>
    <div class="table-responsive center-block">
        <table id="users" class="table table-bordred table-striped">
            <thead>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Admin</th>
            <th>Date</th>
            <th>Actions</th>
            </thead>
            <tbody>
            <c:forEach var="obj" items="${userList.pageList}">
                <tr>
                    <td><c:out value="${obj.id}"/></td>
                    <td><c:out value="${obj.name}"/></td>
                    <td><c:out value="${obj.age}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${obj.getAdmin() == true}"><span
                                    class="glyphicon glyphicon-ok"></span></c:when>
                            <c:otherwise><span class="glyphicon glyphicon-remove"></span></c:otherwise>
                        </c:choose>
                    </td>
                    <td><fmt:formatDate value='${obj.createdDate}' pattern='dd.MM.yyyy'/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/single?userid=${obj.id}"
                           class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span></a>
                        <a href="${pageContext.request.contextPath}/deleteUser?userid=${obj.id}"
                           class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${userList ne null and userList.firstPage ne true}">
            <a href="${pageContext.request.contextPath}/?type=prev" type="button"
               class="btn btn-primary btn-md prev-button">&#8592; Previous</a>
        </c:if>
        <c:if test="${userList ne null and userList.lastPage ne true}">
            <a href="${pageContext.request.contextPath}/?type=next" type="button"
               class="btn btn-primary btn-md next-button">Next &#8594;</a>
        </c:if>
    </div>
    <a href="${pageContext.request.contextPath}/single?userid=0" type="button"
       class="btn btn-success center-block addbutton">Add</a>
</div>
</body>
</html>
