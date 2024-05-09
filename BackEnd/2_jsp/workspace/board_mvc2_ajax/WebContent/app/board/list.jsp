<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link href="${cp}/css/style.css" rel="stylesheet">
</head>
<body class="list">
	<c:if test="${empty loginUser }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace("${cp}/");
		</script>
	</c:if>
	<c:if test="${cookie.w.value != null and cookie.w.value == 'f'}">
		<script>alert("게시글 작성 실패!");</script>	
	</c:if>
	<div id="wrap" class="list">
		<div></div>
		<!-- 로그아웃 버튼 배치할 테이블 -->
		<table class="header_area">
			<tbody>
				<tr>
					<td>
						<span>${loginUser}님 환영합니다.</span>
						<a class="btn" href="${cp}/api/user/logout">로그아웃</a>
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
		<!-- 게시글 리스트 띄워주는 테이블 -->
		<div class="tar w900 totalCnt"></div>
		<div class="list table">
			<div class="thead tac">
				<div class="row">
					<div>번호</div>
					<div>제목</div>
					<div>작성자</div>
					<div>날짜</div>
					<div>조회수</div>
				</div>
			</div>
			<div class="tbody">
			</div>
		</div>
		<!-- 페이징 처리하는 테이블 -->
		<table class="pagination">
			<tbody>
				<tr>
					<td>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- 글쓰기 버튼 배치하는 테이블 -->
		<table class="btn_table">
			<tbody>
				<tr>
					<td>
						<a class="write btn" href="${cp}/board/write?page=${page}&keyword=${keyword}">글쓰기</a>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- 검색 -->
		<div class="search_area">
			<input type="search" id="keyword" name="keyword" value="${keyword}">
			<input type="button" value="검색" onclick="search();">
		</div>
	</div>
	<div id="chat-circle" class="btn btn-raised">
		<div id="chat-overlay"></div>
		<span class="material-symbols-outlined">speaker_phone</span>
	</div>
	<div class="chat-box">
		<div class="chat-box-header">
			사용자 채팅 <span class="chat-box-toggle"><span
				class="material-symbols-outlined">close</span></span>
		</div>
		<div class="chat-box-body">
			<div class="chat-box-overlay"></div>
			<div class="chat-logs"></div>
			<!--chat-log -->
		</div>
		<div class="chat-input">
			<form>
				<input type="hidden" id="userid" name="userid" value="${loginUser}">
				<span class="echo-receiver"></span> <input type="text"
					id="chat-input" placeholder="Send a message..."
					onkeyup="sendEcho();" />
				<button type="submit" class="chat-submit" id="chat-submit">
					<span class="material-symbols-outlined">send</span>
				</button>
			</form>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	window.onload = function(){
		window.setTimeout(function(){
			document.querySelector("#wrap>div:nth-child(1)").style.display="none";
		},1300)
		
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					const data = JSON.parse(xhr.responseText)
					$(".totalCnt").text("글 개수 : "+data.totalCnt);
					let str = "";
					if(data.list.length > 0){
						for(let i=0;i<data.list.length;i++){
							let board = data.list[i]
							str += '<div class="row">'
							str += '<div>'+board.boardnum+'</div>'
							str += '<div>'
							if(data.hot_board[i] == 'O'){
								str += '<sup class="hot">Hot</sup>' 
							}
							str += '<a href="${cp}/board/view?boardnum='+board.boardnum+'&page='+data.page+'&keyword='+data.keyword+'">';
							str += board.boardtitle+'<span id="reply_cnt">['+data.reply_cnt_list[i]+']</span>'
							str += '</a>'
							str += '</div>'
							str += '<div>'+board.userid+'</div>'
							str += '<div>'+board.regdate+'</div>'
							str += '<div>'+board.readcount+'</div>'
							str += '</div>'
						}
					}
					else{
						str += '<div class="row no-list"><div>등록된 게시글이 없습니다.</div></div>'
					}
					$(".list.table .tbody").append(str);
					
					str = "";
					if(data.startPage != 1){
						str += '<a class="btn" href="${cp}/board/list?page='+(data.startPage-1)+'&keyword='+data.keyword+'">&lt;</a>'
					}
					for(let i=data.startPage; i<=data.endPage; i++){
						if(data.page == i){
							str += '<span class="nowPage">'+i+'</span>'
						}
						else{
							str += '<a class="btn" href="${cp}/board/list?page='+i+'&keyword='+data.keyword+'">'+i+'</a>'
						}
					}
					if(data.endPage != data.totalPage){
						str += '<a class="btn" href="${cp}/board/list?page='+(data.endPage+1)+'&keyword='+data.keyword+'">&gt;</a>'
					}
					$("table.pagination tr td").append(str);
					
					const regdate = document.querySelectorAll(".list .tbody .row div:nth-child(4)");
					console.log(regdate)
					const now = new Date();
					for(let td of regdate){
						const time = new Date(td.innerText);
						
						if((now.getTime() - time.getTime()) <= 5*60*60*1000){
							const statusTag = td.previousElementSibling.previousElementSibling;
							statusTag.innerHTML = '<sup class="new">New</sup>'+statusTag.innerHTML;
						}
					}
				}
			}
		}
		xhr.open("GET","${cp}/api/board/list?page=${param.page}&keyword=${param.keyword}");
		xhr.send();
		
	}
	function search(){
		const keyword = document.getElementById("keyword");
		//유효성 검사
		location.replace("${cp}/board/list?keyword="+keyword.value);
	}
	
	
	
	//채팅 구현
	let socket = null;
	let INDEX = 0;
	//현재 이 클라이언트(세션)이 귓속말을 보내는 중인지 확인하기 위한 flag
	//귓속말을 보내게 되면 이 안에는 "/userid " 형태의 문자열이 담긴다.
	let echoFlag = "";
	//채팅 버튼 클릭시 전체 채팅에 접속, 채팅창 열어주기
	$("#chat-circle").click(function(){
		$("#chat-circle").toggle('scale');
		$(".chat-box").toggle('scale');
		socket = new WebSocket('ws://localhost:9090/chat/'+$('#userid').val());
		
		//소켓 연결에 오류가 발생했을 때 수행할 이벤트
		socket.onerror = function(e){}
		//소켓 연결이 열렸을 때 수행할 이벤트
		socket.onopen = function(e){
			socket.send('in : '+$('#userid').val())
		}
		//소켓 연결을 통해 메세지가 전달되었을 때 수행할 이벤트
		socket.onmessage = function(e){
			onMessage(e);
		}
		//소켓 연결을 종료할 때 수행할 이벤트
		socket.onclose = function(e){
			$(".chat-logs").html("");
		}
	})
	//닫기 버튼을 클릭했을 때 호출될 이벤트, 창 닫기, 소켓 접속 종료
	$(".chat-box-toggle").click(function(){
		socket.send('out : '+$("#userid").val());
		$("#chat-circle").toggle('scale');
		$(".chat-box").toggle('scale');
		socket.close();
		socket = null;
	})
	$("#chat-submit").click(function(e){
		e.preventDefault();
		let msg = $("#chat-input").val();
		msg = msg.trim();
		//하이
		// /banana 하이
		//귓속말을 보냈을 경우도 있으므로 msg 앞에 echoFlag를 연결하여
		//채팅 로그를 만들 때와 서버측에서 메세지를 처리할 때 귓속말로 처리를 할 수 있도록 만들어 준다.
		msg = echoFlag+msg;
		if(msg == ""){
			return false;
		}
		//DOM 구현되어 있는 채팅 로그 생성 함수 호출
		generate_message(msg,'self');
		//appleKHGB안녕
		socket.send($("#userid").val()+"KHGB"+msg);
	})
	
	
	//클라이언트 측에서 메세지를 받았을 때 호출될 이벤트 함수
	function onMessage(e){
		//in : banana
		const message = e.data;
		//in : ---> 누군가가 접속해서 메세지가 날라온 경우
		if(message.substring(0,4) == "in :"){
			//5번 인덱스부터 잘라내면 접속한 사람의 아이디
			let inId = message.substring(5);
			str = '<div class="inout-Msg">'+inId+'님이 들어오셨습니다.</div>';
			$(".chat-logs").append(str);
		}
		//out : ---> 누군가가 종료하면서 메세지가 날라온 경우
		else if(message.substring(0,5) == "out :"){
			let outId = message.substring(6);
			str = '<div class="inout-Msg">'+outId+"님이 나가셨습니다.</div>";
			$(".chat-logs").append(str);
		}
		else{
			let idx = message.indexOf("KHGB");
			let sender = message.substring(0,idx);
			// /banana 하이
			let content = message.substring(idx+4);
			
			// /apple
			let temp = content.split(" ")[0];
			//띄어쓰기로 split된 것 중 맨 앞의 것의 첫 글자가 "/" 라면 귓속말이라는 뜻 
			if(temp.substring(0,1) == "/"){
				//그 "/" 뒤에 적힌것이 내 아이디라면 나에게 온 귓속말이라는 뜻
				if(temp.replace("/","") == $("#userid").val()){
					// 앞의 /userid 는 건너뛴 후의 실제 내용만 넘겨주며 채팅 로그 생성
					generate_message(content.substring(temp.length+1),"echo",sender);
				}
				//나에게 온 귓속말이 아니라는 뜻
				else{
				}
			}
			//일반적인 메세지이므로
			else{
				//일반적인 채팅 로그로 생성
				generate_message(content,"user",sender);
			}
		}
	}
	
	//채팅창에 /아이디 를 쓰면 귓속말로 바뀌어야 하므로 키업 이벤트 함수로 판단해준다.
	function sendEcho(){
		//스페이스바를 눌렀을 때
		if(window.event.keyCode == 32){
			//채팅창의 첫번째 글자가 / 라면
			if($("#chat-input").val().substring(0,1) == "/"){
				// / 기준으로 나눈 뒤의것은 귓속말을 원하는 사람의 아이디이다.
				//ex) "/banana " 
				let userid = $("#chat-input").val().split("/")[1].trim();
				checkUserId(userid);
			}
		}
	}
	//귓속말 상대가 존재하는 아이디인지 확인하는 함수
	function checkUserId(userid){
		if(userid == $("#userid").val()){
			alert("자신에게는 귓속말이 불가능합니다!");
			$("#chat-input").val("")
			$("#chat-input").focus();
		}
		else{
			const xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						let txt = xhr.responseText.trim();
						if(txt == "X"){
							//귓속말이 가능하다는 뜻이므로 echoFlag에 "/상대아이디 " 를 넣어준다
							echoFlag = $("#chat-input").val();

							//채팅창은 귓속말 상태로 디자인 변경
							$("#chat-input").addClass("echo");
							$(".echo-receiver").text("["+userid+"]에게 : ")
							$(".echo-receiver").css("width","fit-content");
							$(".echo-receiver").css("padding","0 0 0 10px");
							//					40px							40
							let w = 299 - Number($(".echo-receiver").css("width").replace("px",""));
							
							$("#chat-input").css("width",w+"px");
							$("#chat-input").css("padding-left","0");
							
							//귓속말을 취소할 수 있는 이벤트 생성
							$(".echo-receiver").click(function(e){
								$("#chat-input").removeClass("echo");
								$("#chat-input").attr("style","");
								$(".echo-receiver").attr("style","");
								$(".echo-receiver").text("");
								echoFlag = "";
							})
						}
						else{
							alert("잘못된 아이디입니다!");
						}
						$("#chat-input").val("")
						$("#chat-input").focus();
					}
				}
			}
			xhr.open("GET","${cp}/user/checkid?userid="+userid);
			xhr.send();
		}
	}
	
	//채팅 로그 생성하는 함수
	//msg : 실제 내용이 될 텍스트 / type : 'self'(내가 보낸 모습으로),'user'(남이 보낸 모습으로)
	function generate_message(msg, type, sender){
		console.log(msg);
		INDEX++;
		var str = "";
		let receiver = "";
		let content = "";
		// /banana 
		if(echoFlag != ""){
			let temp = msg.split(" ")[0];
			console.log(temp)
			receiver = echoFlag.substring(1).trim();
			content = msg.substring(temp.length+1) 
		}
		else{
			content = msg;
		}
		//										만약 내가 귓속말을 보낸 상태라면 self와 echo 클래스 둘 다 달려야 함
		str += "<div id='cm-msg-"+INDEX+"' class=\"chat-msg "+type+(echoFlag!=""?" echo":"")+"\">";
		str += "<span class=\"msg-avatar\">";
		str += "<img src=\"${cp}/images/chat_bg2.jpeg\">";
		str += "<\/span>";
		str += "<div class=\"cm-msg-text\">";
		str += content;
		str += "<\/div>";
		//남이 보낸 메세지를 로그로 찍을 때
		if(sender){
			//귓속말로 날라왔을 때
			if(type == 'echo'){
				str += "<div class='receiver echo'>["+sender+"]의 귓속말</div>";
			}
			//일반 채팅으로 날라왔을 때
			else{
				str += "<div class='receiver'>["+sender+"]</div>";
			}
		}
		//내가 보낸 메세지를 로그로 찍을 때
		else{
			//귓속말로 보냈을 때
			if(echoFlag != ""){
				str += "<div class='receiver echo'>"+receiver+"에게</div>"
			}
			//일반 채팅으로 보냈을 때
			else{
				str += "<div class='receiver'>모두에게</div>"
			}
		}
		str += "<\/div>";
		$(".chat-logs").append(str);
		$("#cm-msg-" + INDEX).hide().fadeIn(300);
		if (type == 'self') {
			$("#chat-input").val('');
		}
		$(".chat-logs").stop().animate({
			scrollTop : $(".chat-logs")[0].scrollHeight
		}, 1000);
		
		
		const arMsg = document.getElementsByClassName("cm-msg-text");
	
		for (let i = 0; i < arMsg.length; i++) {
			let maxLineWidth = arMsg[i].offsetWidth;
	
			let textContent = arMsg[i].textContent;
	
			let textWords = textContent.split("");
	
			let newContent = "";
	
			let tempContent2 = "";
			
			let first = true;
			for (let j = 0; j < textWords.length; j++) {
				let word = textWords[j];
				const tempContent = newContent + word;
				const tempElement = document.createElement('div');
				tempElement.classList = 'tempEl';
				tempElement.textContent = tempContent2;
	
				tempElement.style.display = 'inline-block';
	
				arMsg[i].appendChild(tempElement);
				
				if(tempElement.offsetWidth >= 160 && first){
					newContent += word;
					tempContent2 += word;
					first = false;
				} else if (tempElement.offsetWidth > 159.8) {
					newContent += '<br>' + word;
					tempContent2 = "";
				} else {
					newContent += word;
					tempContent2 += word;
				}
			}
			arMsg[i].innerHTML = newContent.trim();
		}
	}
</script>
</html>







