<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최종 예제 게시판</title>
<link rel="stylesheet" href="${cp}/css/style.css">
</head>
<body class="login">
	<c:if test="${not empty cookie.joinid.value}">
		<script>
			alert("${cookie.joinid.value}님 가입을 환영합니다~");
			document.loginForm.userpw.focus();
		</script>
	</c:if>
	<c:if test="${not empty loginUser}">
		<script>
			location.replace("${cp}/boardlist.bo")
		</script>
	</c:if>
	<div id="wrap" class="login">
		<form action="${cp}/user/loginok" method="post" name="loginForm">
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" name="userid" value="${cookie.joinid.value}">
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" name="userpw">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="btn_area">
								<input type="submit" value="로그인">
								<a class="btn" href="${cp}/user/join">회원가입</a>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<img alt="" src="${cp}/images/타이틀.png">
</body>
<script>
	document.cookie = "joinid=; path=/;"
	window.setTimeout(function(){
		document.querySelector("#wrap.login+img").classList = "hdd";
		document.querySelector("#wrap.login").style.display = "block";
	},1300)
	
	xhr.onre = function(){
		
	}
	xhr.open("GET","boardlist.bo")
</script>
</html>
