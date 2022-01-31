<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel='stylesheet' type='text/CSS' href='WebContent/CSS/style.css'>
	<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
	<title>TODO入力画面</title>
</head>
<body>
	<div class="container-xxl">
		<p class="title">TODO入力画面</p>
	<%
        if(request.getAttribute("error_msg")!=null){%>
                <p class="error-msg"><%=request.getAttribute("error_msg") %></p>
        <%}
	%>
		<form action='/create' method='post' onSubmit="return check()">
			<div class="row">
				<label class="col">新規TODO</label>
			</div>
			<div class="row">
				<div class="col-10">
					<textarea type="text" id="new_todo" name="new_todo" placeholder='新しいTODOを入力してください。'><% if(request.getAttribute("value")!=null){ %><%=request.getAttribute("value")%><% } %></textarea>
				</div>		
			</div>
			<div class="row">
				<div class="col-10 btn-wrapper2">			
					<button type="button" href="/todo" class="btn btn-outline-danger" onclick="location.href='/todo'">キャンセル</button>
					<button id='btn' class="btn btn-outline-success">登録</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		function check(){

			if(window.confirm('登録してよろしいですか？')){
				return true;
			}
			else{
				return false;
			}
		}
	</script>
</body>
</html>