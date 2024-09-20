<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>getview</title>
</head>
<body>
	<p>
		제목 <input type="text" name="boardtitle" value="${board.boardtitle}" readonly>
	</p>
	<p>
		작성자 <input type="text" name="userid" value="${board.userid}" readonly>
	</p>
	<p>
		작성일자 ${board.regdate}
	</p>
	<p>
		내용
		<textarea name="boardcontents" readonly>${board.boardcontents}</textarea>
	</p>
	<hr>
	<div id="btn_area">
		<a href="/user/main">목록</a>
		<c:if test="${board.userid == loginUser}">
			<a href="#">수정</a>
			<a href="#">삭제</a>
		</c:if>
	</div>
</body>
<script>

</script>
</html>















