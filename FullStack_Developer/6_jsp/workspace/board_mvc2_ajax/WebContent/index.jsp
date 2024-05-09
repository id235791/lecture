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
	<c:if test="${not empty loginUser}">
		<script>
			location.replace("${cp}/board/list")
		</script>
	</c:if>
	<div id="wrap" class="login">
		<form name="loginForm">
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
								<input type="button" value="로그인" onclick="login()">
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
	const userid = document.loginForm.userid;
	const userpw = document.loginForm.userpw;
	window.onload = function(){
		window.setTimeout(function(){
			document.querySelector("#wrap.login+img").classList = "hdd";
			document.querySelector("#wrap.login").style.display = "block";
			if(userid.value != ""){
				userpw.focus();
			}else{
				userid.focus();
			}
		},1300)
	}
	function login(){
		const xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function(){
	    	if(xhr.readyState == 4){
	    		if(xhr.status == 200){
	    			alert(userid.value+"님 어서오세요!");
	    			location.replace("${cp}/board/list");
	    		}
	    		else{
	    			alert("로그인 실패!");
	    			location.replace(cp+"/");
	    		}
	    	}
	    }
	    xhr.open("GET","${cp}/api/user/login?userid="+userid.value+"&userpw="+userpw.value);
	    xhr.send();
	}
</script>
</html>
