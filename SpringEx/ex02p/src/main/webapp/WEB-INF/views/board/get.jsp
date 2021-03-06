<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/04/19
  Time: 3:58 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Read</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Read page
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="form-group">
                    <label>Bno</label> <input class="form-control" name='bno' value='<c:out value="${board.bno}"/>' readonly="readonly">
                </div>

                <div class="form-group">
                        <label>Title</label> <input class="form-control" name='title' value='<c:out value="${board.title}"/>' readonly="readonly">
                    </div>

                    <div class="form-group">
                        <label>Text Area</label>
                        <textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content}"/></textarea>
                    </div>

                    <div class="form-group">
                        <label>Writer</label><input class="form-control" name='writer' value='<c:out value="${board.writer}"/>' readonly="readonly">
                    </div>
                    <button data-oper='modify' class="btn btn-default" >Modify</button>
                    <button data-oper='list' class="btn btn-info" >List</button>

                    <form id='operForm' action="/board/modify" method="get">
                        <input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
                        <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
                        <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
                    </form>

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<%@include file="../includes/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function(){

        var operForm = $("#operForm");

        $("button[data-oper='modify']").on("click", function(){

           operForm.attr("action", "/board/modify").submit();

        });

        $("button[data-oper='list']").on("click", function(){
            operForm.find("#bno").remove();
            operForm.attr("action", "/board/list")
            operForm.submit();


        });

    });

</script>

</body>

</html>