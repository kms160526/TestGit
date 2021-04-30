<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/04/28
  Time: 9:33 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

<!-- all or member or domain-->
<h1>/sample/all page</h1>

<sec:authorize access="isAnonymous()">

    <a href="/customLogin">로그인</a>

</sec:authorize>
<sec:authorize access="isAuthenticated()">

    <a href="/customLogout">로그아웃</a>

</sec:authorize>


</body>
</html>
