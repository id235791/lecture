<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>getview</title>
</head>
<body>
	<form name="modifyForm" action="/board/modifyOk" method="post">
		<input type="hidden" name="boardnum" value="${board.boardnum}">
		<p>
			제목 <input type="text" name="boardtitle" value="${board.boardtitle}">
		</p>
		<p>
			작성자 <input type="text" name="userid" value="${board.userid}" readonly>
		</p>
		<p>
			내용
			<textarea name="boardcontents">${board.boardcontents}</textarea>
		</p>
		<hr>
		<div id="btn_area">
			<a href="/user/main">목록</a>
			<a href="javascript:document.modifyForm.submit()">수정완료</a>
		</div>
	</form>
</body>
<script>

</script>
</html>















