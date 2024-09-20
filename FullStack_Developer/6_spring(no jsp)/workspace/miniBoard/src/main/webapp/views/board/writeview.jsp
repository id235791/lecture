<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<html>
<head>
	<title>writeview</title>
</head>
<body>
	<form name="writeForm" action="/board/writeOk" method="post">
		<p>
			제목 <input type="text" name="boardtitle">
		</p>
		<p>
			작성자 <input type="text" name="userid" value="${sessionScope.loginUser}" readonly>
		</p>
		<p>
			내용
			<textarea name="boardcontents"></textarea>
		</p>
		<p>
			<input type="submit" value="작성완료">
		</p>
	</form>
</body>
<script>

</script>
</html>















