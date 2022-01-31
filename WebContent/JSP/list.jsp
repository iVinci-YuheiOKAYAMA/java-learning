<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
	<link rel='stylesheet' type='text/CSS' href="/WebContent/CSS/style.css">
	<title>TODO一覧画面</title>
</head>
<body>
	<div class="container-xxl">
		<div class="row">
			<table class="table table-hover table-striped todo-table">
				<thead>
					<tr>
						<th class="title">TODO</th>
						<th></th>
					</tr>
				</thead>
				<tbody>					
					<c:forEach items="${list}" var="item">
						<tr>
							<td class="big-td"><c:out value="${item.task}" /></td>
							<td class="small-td"">
								<form action='/delete' method='post' onSubmit="return check()">
								<input type='hidden' name='id' value="${item.id}">
								<div class="btn-wrapper">
									<button type='submit' class='btn btn-outline-secondary'>削除</button>
								</div>
								</form>
							</td>		
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<table class="table">
				<tbody>
					<tr class="last-tr">
						<td class="big-td"></td>
						<td class="small-td">
							<div class="btn-wrapper">
								<button type='button' class='btn btn-outline-success' onclick="location.href='/add'">追加</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		function check(){

			if(window.confirm('削除してよろしいですか？')){
				return true;
			}
			else{
				return false;
			}
		}
	</script>
</body>
</html>