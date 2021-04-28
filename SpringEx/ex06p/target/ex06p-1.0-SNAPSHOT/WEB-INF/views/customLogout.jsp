<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/04/28
  Time: 12:54 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

<h1> Logout Page</h1>

<form action="/customLogout" method='post'>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button>로그아웃</button>
</form>

</body>
</html>
