<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" scope="application" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<style>
	*{ margin:0; padding:0; box-sizing: border-box; }
	.chat-container{
		width:400px;
		height:800px;
		margin:20px auto;
		border:1px solid black;
	}
	.log-box{
		border:1px solid blue;
		margin:10px;
		height:620px;
		overflow-y:auto;
		position: relative;
	}
	.input-box{
		border:1px solid green;
		margin:10px;
	}
	.chat-input{
		padding:5px 10px;
		font-size:20px;
		width:300px;
		margin-right:10px;
	}
	.chat-submit, .chat-enter, .chat-out{
		padding:5px 10px;
		width:60px;
	}
	.room{
		display: flex;
		border-bottom:1px solid #ccc;
		padding:10px;
	}
	.room-name{
		width:50%;
		padding-left:10px;
	}
	.room-cnt{
		width:30%;
		text-align: center;
	}
	.no-room{
		padding:10px;
		text-align: center;
	}
	#back{
	    display: inline-block;
	    background: transparent;
	    width: 20px;
	    height: 20px;
	    border: none;
	    border-right: 1px solid #ccc;
	    position: absolute;
	    left: 10px;
	    cursor: pointer;
        font-size: 18px;
    	padding-right: 10px;
	}
	.title{
		width: 100%;
		text-align: center;
		border-bottom:1px solid #ccc;
		padding:10px;
		margin-bottom: 10px;
	}
	.inout-Msg{
	    clear: both;
	    background: #eee;
	    text-align: center;
	    width: 300px;
	    margin: 10px auto;
	    border-radius: 10px;
	    color: black;
	}
	.chat-content{
		width:100%; height:fit-content;
		clear:both;
		padding:0 10px;
		margin-top:10px;
	}
	.msg{
		padding:10px;
		border-radius:10px;
	}
	.sender{
		font-size:12px;
		margin-bottom:10px;
	}
	.chat-content.self > *{
		float:right;
	}
	.chat-content.self > .msg{
		background:aliceblue;
	}
	.chat-content.self .sender{
		clear:both;
	}
	.chat-content.user > *{
		float:left;
	}
	.chat-content.user > .msg{
		background:lemonchiffon;
	}
	.chat-content.user .sender{
		clear:both;
	}
	.btn-box{
		text-align: center;
		margin-bottom:20px;
	}
	.btn-box *{
		padding:10px;
	}
	.out-box{
		width:fit-content;
		margin:0 auto;
	}
</style>
</head>
<body>
	<div class="chat-container">
		<div class="log-box"></div>
		<div class="input-box">
			<input type="text" class="chat-input">
			<input type="button" class="chat-enter" value="입장">
			<input type="button" class="chat-submit" value="보내기" style="display:none;">
		</div>
		<input type="hidden" class="userid" value="">
		<div class="btn-box" style="display:none;">
			<input type="button" id="create" value="방만들기">
			<input type="button" id="out" value="방나가기" style="display:none;">
		</div>
		<div class="out-box">
			<span class="session-text" style="display:none;"></span>
			<input type="button" class="chat-out" value="종료" style="display: none;">
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	let socket = null;
	let isEntered = false;
	const room_list = new Map();
	$(".chat-enter").click(function(e){
		const userid = $(".chat-input").val();
		$(".userid").val(userid);
		socket = new WebSocket('ws://localhost:9090${cp}/chat2/'+userid);
		$(this).hide();
		$(this).next().show();
		$(".chat-out").show();
		$(".chat-input").val("");
		$(".btn-box").show();
		
		//소켓 연결에 오류가 발생했을 때 수행할 이벤트
		socket.onerror = function(e){}
		//소켓 연결이 열렸을 때 수행할 이벤트
		socket.onopen = function(e){
			socket.send('in : '+userid)
			$(".session-text").text(userid+"로 접속 중")
			$(".session-text").show();
		}
		//소켓 연결을 통해 메세지가 전달되었을 때 수행할 이벤트
		socket.onmessage = function(e){
			onMessage(e);
		}
		//소켓 연결을 종료할 때 수행할 이벤트
		socket.onclose = function(e){
			$(".log-box").html("");
			$(".session-text").text("")
			$(".session-text").hide();
		}
	})
	$(".chat-out").click(function(e){
		socket.send('out : '+$(".userid").val());
		$(".chat-input").val("");
		$(".userid").val("");
		socket.close();
		socket = null;
		$(this).hide();
		$(".chat-enter").show();
		$(".chat-enter").next().hide();
	})
	$(".chat-submit").click(function(e){
		const content = $(".chat-input").val().trim();
		if(content == ""){
			return;
		}
		generate_message(content,'self');
		socket.send("normal : "+$(".userid").val()+"/"+content);
	})
	$("#create").click(function(e){
		const name = prompt("방 제목을 입력하세요");
		socket.send("create : "+name);
		isEntered = true;
		str = "";
		str += "<div class='title'>"+"<input id='back' type='button' value='<' onclick='back()'>"+name+"</div>"
		str += '<div class="inout-Msg">'+name+' 채팅방이 생성되었습니다.</div>'
		$(".log-box").html(str);
		$('#create').hide();
		$('#out').show();
	})
	function onMessage(e){
		const message = e.data;
		if(message.substring(0,9) == "display :"){
			const json = JSON.parse(message.substring(10));
			if(!isEntered){
				displayRooms(json);
			}
		}
		else if(message.substring(0,8) == "normal :"){
			let idx = message.indexOf("/")
			let sender = message.substring(9,idx);
			// /banana 하이
			let content = message.substring(idx+1);
			
			// /apple
			generate_message(content,"user",sender);
		}
	}
	function displayRooms(rooms){
		let str = "";
		console.log(rooms);
		let flag = false;
		for(const idx in rooms){
			flag = true;
			const room = rooms[idx]
			str += "<div class='room'>"
			str += "<div class='room-name'>"+room.name+"</div>"
			str += "<input type='button' value='참여하기' onclick='roomEnter(\""+room.pk+"\",\""+room.name+"\")'>"
			str += "<div class='room-cnt'>"+room.members.length+"명 참여 중</div>"
			str += "</div>"
		}
		if(flag){
			$(".log-box").html(str);
		}
		else{
			$(".log-box").html("<div class='no-room'>생성된 채팅방이 없습니다.</div>")
		}
	}
	function roomEnter(pk,name){
		isEntered = true;
		socket.send("enter : "+pk);
		str = "";
		str += "<div class='title'>"+"<input type='button' id='back' value='<' onclick='back()'>"+name+"</div>"
		$(".log-box").html(str);
	}
	function back(){
		isEntered = false;
		socket.send("display");
	}
	function generate_message(msg, type, sender){
		let str = "";
		str += "<div class='chat-content "+type+"'>"
		str += "<div class='msg'>"+msg+"</div>"
		str += "<div class='sender'>"
		if(type == 'self'){
			str += "모두에게"
		}
		else if(type == 'user'){
			str += sender+"이(가)"
		}
		str += "</div>"
		str += "</div>"
		
		$(".log-box").append(str);
		const elem = document.getElementsByClassName("log-box")[0]
        elem.scrollTop = elem.scrollHeight;
	}
</script>
</html>






