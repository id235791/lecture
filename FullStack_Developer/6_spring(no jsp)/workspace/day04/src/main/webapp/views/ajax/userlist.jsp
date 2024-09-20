<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<html>
<head>
	<title>userlist</title>
</head>
<body>
	<table border="1">
		<tbody id="tbody">
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
			</tr>
		</tbody>
	</table>
	<hr>
	<input type="button" value="데이터 불러오기" onclick="f()">
</body>
<script>
	function f(){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					const list = JSON.parse(xhr.responseText)
					
					let str = "";
					for(let user of list){
						str += "<tr>";
						str += "<td>"+user.userid+"</td>"
						str += "<td>"+user.userpw+"</td>"
						str += "<td>"+user.username+"</td>"
						str += "</tr>"
					}
					
					const tbody = document.getElementById("tbody");
					tbody.innerHTML += str;
				}
			}
		}
		//1. POST 방식으로 전송시
		//xhr.open("POST","/ajax/list");
		//xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
		//POST 방식의 통신 때 파라미터를 보내주려면 아래와 같은 방식으로 전송한다.
		//xhr.send("cnt=3");
		
		xhr.open("GET","/ajax/list");
		xhr.send();
		/*
		//fetch 통신 시도
		fetch('list',{method:"GET"})
		//결과 응답을 받아서 json 변환 후 return
		.then(resp=>{return resp.json()})
		//return 된 객체(유저들이 담겨있는 배열)를 data 변수로 받아서 내부 수행
		.then(data => {
			let str = "";
			for(let user of data){
				str += "<tr>";
				str += "<td>"+user.userid+"</td>"
				str += "<td>"+user.userpw+"</td>"
				str += "<td>"+user.username+"</td>"
				str += "</tr>"
			}
			const tbody = document.getElementById("tbody");
			tbody.innerHTML += str;
		})
		*/
	}
</script>
</html>




