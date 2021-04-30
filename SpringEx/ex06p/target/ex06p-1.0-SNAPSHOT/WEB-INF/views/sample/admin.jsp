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
<h1>/sample/admin page</h1>

<p>principal : <sec:authentication property="principal" /></p>
<p>MemberVO : <sec:authentication property="principal.member" /></p>
<p>사용자이름 : <sec:authentication property="principal.member.userName" /></p>
<p>사용자아이디 : <sec:authentication property="principal.username" /></p>
<p>사용자 권한 리스트 : <sec:authentication property="principal.member.authList" /></p>

<a href="/customLogout">Logout</a>

</body>
</html>
