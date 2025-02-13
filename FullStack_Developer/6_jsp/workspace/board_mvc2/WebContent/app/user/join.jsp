<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="${cp}/css/style.css">
</head>
<body>
	<c:if test="${not empty loginUser}">
		<script>
			location.replace("${cp}/boardlist.bo")
		</script>
	</c:if>
    <div id="wrap" class="join">
        <form action="${cp}/userjoinok.us" method="post" name="joinForm">
            <table>
                <tbody>
                    <tr>
                        <td colspan="2" id="result"></td>
                    </tr>
                    <tr>
                        <th><label for="userid">아이디</label></th>
                        <td>
                            <input type="text" name="userid" id="userid">
                            <input type="button" value="중복검사" onclick="checkId()">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="userpw">비밀번호</label></th>
                        <td><input type="password" name="userpw" id="userpw" onkeyup="pwcheck()"></td>
                        <!-- onkeyup : 키가 올라갈 때 수행, 키보드에서 손을 뗄 때 -->
                    </tr>
                    <tr>
                        <th><label for="userpw_re">비밀번호 확인</label></th>
                        <td><input type="password" name="userpw_re" id="userpw_re" onkeyup="pwcheck()"></td>
                    </tr>
                    <tr>
                    	<th colspan="2">
                    		<div class="pw_check">
                    			<span>영어 대문자, 소문자, 숫자, 특수문자(~,?,!,@,-)를 모두 하나 이상 포함해야 해요 😀</span>
                    			<span>최소 8자 이상의 비밀번호가 보안에 안전해요 😄</span>
                    			<span>같은 문자가 연속해서 사용되지 않았어요 😆</span>
                    			<span>사용할 수 없는 문자가 포함되지 않았어요 😊</span>
                    			<span>비밀번호 확인이 완료되었어요! 🤗</span>
                    		</div>
                    	</th>
                    </tr>
                    <tr>
                        <th><label for="username">이름</label></th>
                        <td><input type="text" name="username" id="username"></td>
                    </tr>
                    <tr class="gender_area">
                        <th>성별</th>
                        <td>
                            <div>
                            	<ul>
                            		<li class="radio_item">
                            			<input type="radio" id="usergender1" name="usergender" value="M"><label for="usergender1">남자</label>
                            		</li>
                            		<li class="radio_item">
                            			<input type="radio" id="usergender2" name="usergender" value="W"><label for="usergender2">여자</label>
                           			</li>
                            	</ul>
                            	<ul>
                            		<li class="radio_item">
                            			<input type="radio" id="foreigner1" name="foreigner" value="K"><label for="foreigner1">내국인</label>
                            		</li>
                            		<li class="radio_item">
                            			<input type="radio" id="foreigner2" name="foreigner" value="F"><label for="foreigner2">외국인</label>
                           			</li>
                            	</ul>
                            </div>
                        </td>
                    </tr>
                    <tr class="zipcode_area">
                        <th>우편번호</th>
                        <td>
                            <input type="text" name="zipcode" placeholder="우편번호" id="zipcode" readonly onclick="findAddr()">
                            <input type="button" value="우편번호 찾기" onclick="findAddr()">
                        </td>
                    </tr>
                    <tr class="addr_area">
                        <th>주소</th>
                        <td>
                            <input type="text" name="addr" id="addr" placeholder="주소" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th>상세주소</th>
                        <td>
                            <input type="text" name="addrdetail" id="addrdetail" placeholder="상세주소">
                        </td>
                    </tr>
                    <tr>
                        <th>참고항목</th>
                        <td>
                            <input type="text" name="addretc" id="addretc" placeholder="참고항목" readonly>
                        </td>
                    </tr>
                    <tr class="hobby_area">
                        <th>취미</th>
                        <td>
                            <div>
                            	<div class="hobby_input">
                            		<input type="text" name="hobby" onkeyup="hobbyKeyup()"><input type="button" value="추가" onclick="addHobby();">
                            	</div>
                            	<div class="hobby_list"></div>
                            	<input type="hidden" value="" name="userhobby">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <input type="button" value="가입 완료" onclick="sendit()">
                        </th>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script> const cp = '${cp}';</script>
<script src="${cp}/js/user.js"></script>
</html>



