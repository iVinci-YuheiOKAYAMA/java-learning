<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel='stylesheet' type='text/CSS' href='style.css'>
	<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
	<title>TODO一覧画面</title>
</head>
<body>	
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>TODO</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><c:out value="${item.task}" /></td>
					<td><form action='/delete' method='post' onSubmit="return check()"><input type='hidden' name='id' value="${item.id}"><button id='button' type='submit' class='btn btn-danger'>削除</button></form></td>		
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type='button' class='btn btn-success' onclick="location.href='/add'">追加</button>
	<script>
		function check(){

			if(window.confirm('削除してよろしいですか？')){
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

