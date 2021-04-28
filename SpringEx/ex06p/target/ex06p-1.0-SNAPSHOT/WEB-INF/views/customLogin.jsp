<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/04/28
  Time: 11:07 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

<h1>Custom Login Page</h1>
<h2><c:out value="${error}"/></h2>
<h2><c:out value="${logout}"/></h2>

<form method='post' action="/login">

    <div>
        <input type='text' name='username' value='admin'>
    </div>

    <div>
        <input type='password' name='password' value='admin'>
    </div>

    <div>
        <div>
            <input type='checkbox' name='remember-me'> Remember Me
        </div>

        <div>
            <input type='submit'>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </div>
</form>

</body>
</html>
