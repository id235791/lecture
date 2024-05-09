<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>get</title>
<link href="${cp}/css/style.css" rel="stylesheet">
</head>
<body class="get">
	<c:if test="${empty loginUser }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace("${cp}/");
		</script>
	</c:if>
	<div id="wrap" class="get">
		<div></div>
		<!-- 로그아웃 버튼 배치할 테이블 -->
		<table class="header_area">
			<tbody>
				<tr>
					<td>
						<span>${loginUser}님 환영합니다.</span>
						<a class="btn" href="${cp}/user/logout">로그아웃</a>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- 타이틀 띄워주는 테이블 -->
		<table class="title">
			<tbody>
				<tr>
					<td>
						<h3>
							<img src="${cp}/images/타이틀.png" class="tit_img">
						</h3>
					</td>
				</tr>
			</tbody>
		</table>
		<table border="1" class="board_area">
			<tr>
				<th>제목</th>
				<td id="boardtitle"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td id="userid"></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td id="readcount"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td id="boardcontents"></td>
			</tr>
		</table>
		<table class="btn_area">
			<tbody>
				<tr>
					<td>
						<a class="btn" href="${cp}/board/list?page=${param.page}&keyword=${param.keyword}">목록</a>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="reply_line">
			<form name="replyForm" method="post" action="${cp}/reply/write">
				<table class="write_box">
					<tbody>
						<tr>
							<td>댓글</td>
							<td>
								<textarea name="replycontents"></textarea>
								<a class="btn" href="javascript:insertReply();">등록</a>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<form name="updateForm" method="post">
				<table class="update_box">
					<tbody></tbody>
				</table>
			</form>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	window.onload = function(){
		window.setTimeout(function(){
			document.querySelector("#wrap>div:nth-child(1)").style.display="none";
		},1200)
		
		$.ajax({
			url:"${cp}/api/board/get?boardnum=${param.boardnum}",
			type:"GET",
			success:function(data){
				data = JSON.parse(data);
				const board = data.board;
				const files = data.files;
				const replies = data.replies;
				console.log(data);
				console.log(board);
				
				$("#boardtitle").text(board.boardtitle);
				$("#userid").text(board.userid);
				$("#readcount").text(board.readcount);
				$("#boardcontents").text(board.boardcontents);
				str = "";
				if(files.length > 0){
					for(let i=0;i<files.length;i++){
						let file = files[i];
						str += "<tr>"
						str += "<th>첨부파일"+(i+1)+"</th>"
						str += "<td>"
						str += "<a href='${cp}/api/board/file?systemname="+file.systemname+"&orgname="+file.orgname+"'>"+file.orgname+"</a>"
						str += "</td>"
						str += "</tr>"
						
						let ext = file.orgname.split(".").pop();
						if(ext == 'jpeg' || 'jpg' || 'png' || 'gif' || 'webp'){
							str += "<tr>"
							str += "<th></th>"
							str += "<td>"
							str += "<img class='thumbnail' src='${cp}/file/"+file.systemname+"'>"
							str += "</td>"
							str += "</tr>"
						}
					}
				}
				else{
					str += "<tr class='no-file'>"
					str += "<td colspan='2'>첨부 파일이 없습니다.</td>"
					str += "</tr>"
				}
				$(".board_area tbody").append(str);
				str = "";
				if(board.userid == '${loginUser}'){
					str += "<a class='btn' href='${cp}/board/update?boardnum="+board.boardnum+"&page=${param.page}&keyword=${param.keyword}'>수정</a>";
					str += " <a class='btn' href='javascript:deleteBoard("+board.boardnum+")'>삭제</a>";
					$(".btn_area td").prepend(str);
				}
				
				const replyForm = document.replyForm;
				str = "";
				if(replies.length > 0){
					for(let i=0;i<replies.length;i++){
						let reply = replies[i];
						str += "<tr>";
						str += "<td>"+reply.userid+"</td>"
						str += "<td>"
						str += "<textarea readonly name='reply"+i+"' id='reply"+i+"' class='replycontents'>"+reply.replycontents+"</textarea>"
						str += "</td>"
						str += "<td>"
						str += reply.regdate
						if(reply.updatechk == 'o'){
							str += "(수정됨)"
						}
						if(reply.userid == '${loginUser}'){
							str += "<div class='btn_area'>"
							str += "<a class='btn' href='javascript:updateReply("+i+")' id='start"+i+"'>수정</a>"
							str += "<a class='btn' href='javascript:updateReplyOk("+i+","+reply.replynum+")' style='display:none;' id='end"+i+"'>수정완료</a>"
							str += "<a class='btn' href='javascript:deleteReply("+reply.replynum+")'>삭제</a>"
							str += "</div>"
						}
						str += "</td>"
						str += "</tr>";
					}
				}
				else{
					str += "<tr>"
					str += "<td colspan='3'>등록된 댓글이 없습니다</td>"
					str += "</tr>"
				}
				$(".update_box tbody").append(str);
			},
			error:function(data){
				alert("!!");
			}
		})
				
	}
	function deleteBoard(boardnum){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					alert("게시글 삭제 성공!")
					location.replace('${cp}/board/list?keyword=${param.keyword}&page=${param.page}');
				}
				else{
					alert("게시글 삭제 실패!");
					location.reload();
				}
			}
		}
		xhr.open("DELETE","${cp}/api/board/delete?boardnum="+boardnum);
		xhr.send();
	}
	const replyForm = document.replyForm;
	function insertReply(){
		const reply = {
			userid:'${loginUser}',
			replycontents:replyForm.replycontents.value,
			boardnum:${param.boardnum}
		}
		const jsonData = JSON.stringify(reply);
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					alert("댓글 등록 성공!")
					location.reload();
				}
				else{
					alert("댓글 등록 실패!");
					location.reload();
				}
			}
		}
		xhr.open("POST","${cp}/api/reply/write");
		xhr.send(jsonData);
	}
	const updateForm = document.updateForm;
	let flag = false;
	function deleteReply(replynum){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					alert("댓글 삭제 성공!")
					location.reload();
				}
				else{
					alert("댓글 삭제 실패!");
					location.reload();
				}
			}
		}
		xhr.open("DELETE","${cp}/api/reply/delete?replynum="+replynum)
		xhr.send();
	}
	function updateReply(i){
		const start = document.getElementById("start"+i);
		const end = document.getElementById("end"+i);
		const reply = document.getElementById("reply"+i);
		
		if(!flag){
			start.style.display = "none";
			end.style.display = "inline-block";
			reply.removeAttribute("readonly");
			flag = true;
		}
		else{
			alert("수정중인 댓글이 있습니다!");
		}
	}
	function updateReplyOk(i,replynum){
		const reply = {
			replynum:replynum,
			replycontents:$("#reply"+i).val()
		}
		const jsonData = JSON.stringify(reply);
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					alert("댓글 수정 성공!")
					location.reload();
				}
				else{
					alert("댓글 수정 실패!");
					location.reload();
				}
			}
		}
		xhr.open("PUT","${cp}/api/reply/update");
		xhr.send(jsonData);
	}
</script>
</html>









