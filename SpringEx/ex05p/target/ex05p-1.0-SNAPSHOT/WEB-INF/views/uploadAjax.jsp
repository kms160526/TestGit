<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/04/22
  Time: 9:49 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
<body>
    <h1>Upload with Ajax</h1>

    <div class='uploadDiv'>
        <input type='file' name='uploadFile' multiple>

        <button id='uploadBtn'>Upload</button>

        <!-- script 시작 -->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
                crossorigin="anonymous">

        </script>
        <script>
            var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
            var maxSize = 5242880;  // 5MB

            function checkExtension(fileName, fileSize){

                if(fileSize >= maxSize){
                    alert("파일 사이즈 초과");
                    return false;
                }

                if(regex.test(fileName)){
                    alert("해당 종류의 파일은 업로드 할 수 없습니다.");
                    return false;
                }

                return true;
            }

            $(document).ready(function () {

                $("#uploadBtn").on("click", function (e) {
                    var formData = new FormData();

                    var inputFile = $("input[name='uploadFile']");

                    var files = inputFile[0].files;

                    console.log(files);

                    // add filedate to formdata
                    for(var i = 0; i < files.length; i++){

                        if(!checkExtension(files[i].name, files[i].size)){
                            return false;
                        }

                        formData.append("uploadFile", files[i]);
                    }

                    $.ajax({
                       url: '${pageContext.request.contextPath}/uploadAjaxAction',
                       processData: false,
                       contentType: false,
                       data: formData,
                       type: 'POST',
                        dataType: 'json',
                       success: function(result){
                           console.log(result);
                           
                       }
                    }); // $.ajax
                });
            });


        </script>
    </div>

</body>
</html>
