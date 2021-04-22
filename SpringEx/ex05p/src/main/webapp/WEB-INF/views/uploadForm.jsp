<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/04/22
  Time: 9:21 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

    <form action="uploadFormAction" method="post" enctype="multipart/form-data">
        <input type="file" name='uploadFile' multiple>
        <button>Submit</button>
    </form>

</body>
</html>
