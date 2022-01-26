<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel='stylesheet' type='text/CSS' href='style.css'>
	<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
	<title>TODO入力画面</title>
</head>
<body>	
	<p>TODO入力画面</p>
	<form action='/create' method='post' onSubmit="return check()">
		<label>新規TODO</label>
		<textarea type="text" id="new_todo" name="new_todo" placeholder='新しいTODOを入力してください。'></textarea>
	<button type="button" href="/" class="btn btn-danger" onclick="location.href='/'">キャンセル</button>
	<button id='btn' class="btn btn-success">登録</button>
	</form>
	<script>
		function check(){

			if(window.confirm('登録してよろしいですか？')){
				return true;
			}
			else{
				window.alert('キャンセルされました');
				return false;
			}
		}
	</script>
</body>
</html>

			