<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>
<link href="${cp}/css/style.css" rel="stylesheet">
</head>
<body class="modify">
	<div id="wrap" class="modify">
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
		</table>																			<!-- 파일 데이터를 전송하기 위한 형식 -->
		<form id="boardForm" method="post" name="boardForm" action="${cp}/api/board/update" enctype="multipart/form-data">
			<input type="hidden" id="boardnum" name="boardnum" value="">
			<table border="1">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" id="boardtitle" name="boardtitle" maxlength="50" value="">
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" id="userid" name="userid" maxlength="50" value="" readonly>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="boardcontents" name="boardcontents"></textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" value="" name="updateCnt" id="updateCnt">
		</form>
		<table class="btn_area">
			<tbody>
				<tr>
					<td>
						<a class="btn" href="javascript:sendit();">수정완료</a>
						<a class="btn" href="${cp}/board/list?page=${param.page}&keyword=${param.keyword}">목록</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	let i = 0;
	let orgSize = 0;
	const updateCnt = $("#updateCnt");
	//로직 구성을 위한 임시 배열(막 사용되며 바뀔 배열)
	let util_arr = null;
	//기존에 업로드 되어있는 파일들의 orgname들(문자열 값)을 담고있는 태그.
	const orgnames = [];
	//기존에 업로드 되어있는 파일들의 systemname들(문자열 값)을 담고있는 태그.
	const systemnames = [];

	//사용자가 수정하게 될 파일들의 이름들을 임시로 저장하는 배열
	let deleteFiles = [];
	
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
				console.log(data);
				console.log(board);
				
				$("#boardnum").val(board.boardnum);
				$("#boardtitle").val(board.boardtitle);
				$("#userid").val(board.userid);
				$("#boardcontents").val(board.boardcontents);
				str = "";
				let len = files.length;
				i = len;
				if(len > 0){
					for(let i=0;i<files.length;i++){
						let file = files[i];
						str += `<tr class='r${"${i}"}'>`
						str += `<th>파일 첨부${"${i+1}"}</th>`
						str += `<td class='file${"${i}"}_cont'>`
						str += "<div>"
						str += `<input type='file' name='file${"${i}"}' id='file${"${i}"}' style='display:none;'>`
						str += `<input type='hidden' name='systemname' value='${"${file.systemname}"}'>`
						str += `<span id='file${"${i}"}name' class='orgname'>${"${file.orgname}"}</span>`
						str += "</div>"
						str += "<div>"
						str += `<a class='btn' href='javascript:upload(${"${i}"},\"${"${file.systemname}"}\")'>파일 선택</a>`
						str += `<a class='btn' href='javascript:cancelFile(${"${i}"},\"${"${file.systemname}"}\")'>첨부 삭제</a>`
						str += "</div>"
						let ext = file.orgname.split(".").pop()
						if(ext == 'jpeg' || 'jpg' || 'png' || 'gif' || 'webp'){
							str += `<img class='thumbnail' src='${cp}/file/${"${file.systemname}"}'>`
						}
						str += "</td>"
						str += "</tr>"
					}
				}
				str += `<tr class='r${"${len}"}'>
					<th>파일 첨부${"${len+1}"}</th>
					<td class='file${"${len}"}_cont'>
						<div>
							<input type='file' name='file${"${len}"}' id='file${"${len}"}' style='display:none'>
							<input type='hidden' name='systemname' value=''>
							<span id='file${"${len}"}name' class='orgname'>선택된 파일 없음</span>
						</div>
						<div>
							<a class='btn' href='javascript:upload(${"${len}"})'>파일 선택</a>
							<a class='btn' href='javascript:cancelFile(${"${len}"})'>첨부 삭제</a>
						</div>
					</td>
				</tr>`
				
				$("#boardForm tbody").append(str);
				orgSize = i;
				
				util_arr = document.getElementsByClassName("orgname");
				
				for(let j=0;j<orgSize;j++){
					orgnames.push(util_arr[j].innerText.trim());
					systemnames.push(util_arr[j].previousElementSibling.value);
				}

				$("[type=file]").change(function(e){
					
					const fileTag = e.target;
					const file = fileTag.files[0];
					const num = Number(fileTag.id.split("e").pop());
					
					if(file == undefined){
						//원본 파일의 systemname들을 담는 배열에 저장된 값
						if(!systemnames[num]){
							//그게 없다는 뜻은 기존에 업로드 되어있던 파일이 아닌 새로 올렸던 파일을 다시 취소하는 경우이므로 cancelFile 진행
							cancelFile(num);
						}
						else{
							//기존 파일을 건드렸다가(다른 파일로 변경했다가) 다시 클릭해서 취소한 경우
							//ex) 현재 temp_sysname : 'day03_배열2.png'
							//기존 파일의 orgname 추출
							let orgname = orgnames[num];
							//파일명이 뜨는 span태그 내부 텍스트로 기존 파일의 orgname 넣기(원래대로 되돌리기)
							$("#file"+num+"name").text(orgname);
							//파일을 업로드 하며 썸네일이 생겼었다면 제거
							$("."+fileTag.id+"_cont .thumbnail").remove();
							//원본 파일의 확장자
							let ext = systemnames[num].split(".")[1];
							//... 가 그림파일이라면 썸네일 다시 만들기
							if(ext == "jpeg" || ext == "png" || ext == "jpg" || ext == "webp" || ext == "gif"){
								const img = document.createElement("img");
								//원본 파일의 경로를 이용해서 src 써주기
								img.setAttribute("src","${cp}/file/"+systemnames[num]);
								img.setAttribute("class","thumbnail");
								document.querySelector("."+fileTag.id+"_cont").appendChild(img);
							}
							
							//기존 파일을 건드렸다가 취소했으므로, 기존 파일은 지워지면 안된다.(deleteFiles에 임시 저장했던 파일명을 없애줘야한다.)
							for(let j=0;j<deleteFiles.length;j++){
								if(deleteFiles[j] == temp_sysname){
									//일단 수정 취소한 파일명이 담겨있던 방에는 undefined 넣어주기(일단 넣어놓고 224번 줄에서 한번에 처리)
									deleteFiles[j] = undefined;
								}
							}
							console.log(deleteFiles);
						}
					}
					else{
						//일반적인 파일 추가 진행
						$("#"+fileTag.id+"name").text(file.name);
						let ext = file.name.split(".").pop();
						if(ext == "jpeg" || ext == "png" || ext == "jpg" || ext == "webp" || ext == "gif"){
							$("."+fileTag.id+"_cont .thumbnail").remove();
							const reader = new FileReader();
							reader.onload = function(ie){
								const img = document.createElement("img");
								img.setAttribute("src",ie.target.result);
								img.setAttribute("class","thumbnail");
								document.querySelector("."+fileTag.id+"_cont").appendChild(img);
							}
							reader.readAsDataURL(file);
						}
						else{
							$("."+fileTag.id+"_cont .thumbnail").remove();
						}
						
						if(fileTag.id == "file"+i){
							const cloneElement = $(".r"+i).clone(true);
							i++;
							cloneElement.attr("class","r"+i);
							cloneElement.children("th").text("파일 첨부"+(i+1));
							cloneElement.children("td").attr("class","file"+i+"_cont");
							
							cloneElement.find("input[type='file']").attr("name","file"+i);
							cloneElement.find("input[type='file']").attr("id","file"+i);
							cloneElement.find("input[type='file']").val("");
							
							cloneElement.find("span").attr("id","file"+i+"name");
							cloneElement.find("span").text("선택된 파일 없음");
							
							cloneElement.find("a")[0].href = "javascript:upload("+i+")";
							cloneElement.find("a")[1].href = "javascript:cancelFile("+i+")";
							
							cloneElement.appendTo("#boardForm tbody")
							
						}
					}
				})
			},
			error:function(data){
				alert("!!");
			}
		})
				
	}
	//${files.size()} : 원래 업로드 되어있는 파일의 개수
	
	
	
	function sendit(){
		//유효성 검사
		//deleteFiles 배열을 Set으로 변환하여 중복되는 값들 제거
		const set = new Set(deleteFiles);
		//다시 set에 있는 요소들 꺼내서 배열로 만들어서 deleteFiles에 넣기
		deleteFiles = [...set];
		//deleteFiles에서 undefined값 제거하기
		deleteFiles = deleteFiles.filter((item) => { return item != undefined })
		updateCnt.val(deleteFiles.join("\\"));
		console.log(updateCnt.val())
		const boardForm = document.boardForm;
		//유효성 검사
		const formData = new FormData(boardForm);
		
		$.ajax({
			url:boardForm.action,
			type:"PUT",
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				alert("게시글 수정 성공!");
				location.replace("${cp}/board/view?boardnum=${param.boardnum}&page=${param.page}&keyword=${param.keyword}");
			},
			error:function(data){
				alert("게시글 수정 실패 ㅠㅠ");
				location.replace("${cp}/board/list?page=${param.page}&keyword=${param.keyword}");
			}
		})
	}
	
	//기존에 업로드 되어있던 파일 중 현재 변화를 시켜줄 파일의 이름
	let temp_sysname = "";
	
	function upload(num,systemname){
		if(systemname == undefined){
			//기존 파일을 건드리지 않고 새롭게 업로드
			$("#file"+num).click();
		}
		else{
			//기존 파일을 건드리면서 업로드 버튼 클릭
			//변화 시켜줄 파일의 이름을 임시 저장
			//ex)temp_sysname = 'day03_배열2.png'
			temp_sysname = systemname;
			//변화될 파일명(삭제되어야 할 수도 있는 파일명)을 deleteFiles 배열에 추가
			deleteFiles.push(systemname);
			$("#file"+num).click();
		}
		
	}
	
	function cancelFile(num,systemname){
		if(num == i){
			return;
		}
		//넘겨준 systemname을 일단 deleteFiles 배열에 추가
		//기존 파일을 삭제하는 경우라면? systemname : 기존 파일의 systemname
		//새롭게 추가했던 파일을 삭제하는 경우라면? systemname : undefined
		deleteFiles.push(systemname);
		//삭제하려는 행 번호가 orgSize보다 작다면 기존에 업로드 되었던 파일을 삭제하는 경우이므로
		if(num<orgSize){
			//원본 파일들의 orgname과 systemname을 저장하고 있던 배열에서 해당 파일의 정보들을 삭제
			orgnames.splice(num,1);
			systemnames.splice(num,1);
		}
		$(".r"+num).remove();
		//지워진 다음 행 부터 숫자 바꿔주기
		for(let j=num+1;j<=i;j++){
			console.log(j);
			const el = $("#boardForm tbody .r"+j);
			el.attr("class","r"+(j-1));
			
			el.find("th").text("파일 첨부"+j);
			el.find("td").attr("class","file"+(j-1)+"_cont");
			
			el.find("input[type=file]").attr("name","file"+(j-1));
			el.find("input[type=file]").attr("id","file"+(j-1));
			
			el.find("span").attr("id","file"+(j-1)+"name");
			
			//만약 지워진 다음 행에 systemname이 있었다면 upload(행번호,'apple.png') 형태로 a태그의 href를 달아주어야 함
			//따라서 systemname 값을 그대로 연결하면  upload(행번호,apple.png) 형태로 달리기 때문에
			//문자열 값으로 넘기기 위해 systemname이 존재하는 경우에는 양 옆에 ' 를 연결해주는 작업
			let systemname = systemnames[j-1] == undefined ? undefined : "'"+systemnames[j-1]+"'";
			
			//jsp에서 javascript 백쿼트를 이용한 템플릿 문자열을 쓰는 방법
			/*
				참고
				https://yermi.tistory.com/entry/JSP-JSP%EC%97%90%EC%84%9C-JavaScript-%ED%85%9C%ED%94%8C%EB%A6%BF-%EB%AC%B8%EC%9E%90%EC%97%B4-Template-literals-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95-JavaScript-%EB%B0%B1%ED%8B%B1-%EC%82%AC%EC%9A%A9%EC%9D%B4-%EC%95%88%EB%90%A0-%EB%95%8C
			*/
			//만들어지는 문자열 예시
			//원본이 없던 행이라면 -> javascript:upload(0,undefined)
			//원본이 있던 행이라면 -> javascript:upload(0,'apple.png')
			let format = `javascript:upload(${"${j-1}"},${"${systemname}"})`
			console.log("format",format);
			//위의 방법을 이용해서 a태그들의 href 달아주기
			el.find("a")[0].href = `javascript:upload(${"${j-1}"},${"${systemname}"})`;
			el.find("a")[1].href = `javascript:cancelFile(${"${j-1}"},${"${systemname}"})`;
		}
		i--;
		console.log(deleteFiles);
	}
</script>
</html>








