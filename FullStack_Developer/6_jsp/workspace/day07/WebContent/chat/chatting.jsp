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
		height:730px;
		margin:20px auto;
		border:1px solid black;
	}
	.log-box{
		border:1px solid blue;
		margin:10px;
		height:620px;
		overflow-y:auto;
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
		<div class="out-box">
			<span class="session-text" style="display:none;"></span>
			<input type="button" class="chat-out" value="종료" style="display: none;">
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	let socket = null;
	
	$(".chat-enter").click(function(e){
		const userid = $(".chat-input").val();
		$(".userid").val(userid);
		socket = new WebSocket('ws://localhost:9090${cp}/chat/'+userid);
		$(this).hide();
		$(this).next().show();
		$(".chat-out").show();
		$(".chat-input").val("");
		
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
	
	function onMessage(e){
		//in : banana
		const message = e.data;
		//in : ---> 누군가가 접속해서 메세지가 날라온 경우
		if(message.substring(0,4) == "in :"){
			//5번 인덱스부터 잘라내면 접속한 사람의 아이디
			let inId = message.substring(5);
			str = '<div class="inout-Msg">'+inId+'님이 들어오셨습니다.</div>';
			$(".log-box").append(str);
		}
		//out : ---> 누군가가 종료하면서 메세지가 날라온 경우
		else if(message.substring(0,5) == "out :"){
			let outId = message.substring(6);
			str = '<div class="inout-Msg">'+outId+"님이 나가셨습니다.</div>";
			$(".log-box").append(str);
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






