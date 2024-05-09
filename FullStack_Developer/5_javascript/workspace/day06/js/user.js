const inputs = document.querySelectorAll("[type=text]");
for(let tag of inputs){
    tag.addEventListener('blur',blur);
}
const pass = document.querySelectorAll("[type=password]");
for(let tag of pass){
    tag.addEventListener('blur',blur);
}
const result = document.getElementById("result");
let varcheck = false;
userid.focus();

//모든 입력창들에 blur 이벤트 발생 시 수행할 이벤트리스너
function blur(e){
    //어떤 대상에게 blur 이벤트가 발생했는지 구별하기 위해 e.target 값으로 구별
    const target = e.target;

    //분기처리
    if(target.id == "userid"){
        if(target.value == ""){
            result.innerHTML = "아이디를 입력하세요!";
            target.focus();
            varcheck = false;
        }
        else if(userid.value.length<5 || userid.value.length > 12){
            result.innerHTML = "아이디는 5자 이상 12자 이하로 입력하세요!";
            target.focus();
            target.value="";
            varcheck = false;
        }
        else{
            result.innerHTML = "";
        }
    }
    else if(target.id == "userpw"){
        if(target.value == ""){
            result.innerHTML = "비밀번호를 입력하세요!";
            target.focus();
            varcheck = false;
        }
        else if(userpw.value.length < 8){
            result.innerHTML = "비밀번호는 8자 이상으로 입력하세요!";
            target.value = "";
            target.focus();
            varcheck = false;
        }
        else{
            result.innerHTML = "";
        }
    }
    else if(target.id == "userpw_re"){
        if(target.value != userpw.value){
            result.innerHTML = "비밀번호 확인을 다시 해주세요!";
            userpw.focus();
            varcheck = false;
        }
        else{
            result.innerHTML = "";
        }
    }
    else if(target.id == "username"){
        const exp_name = /^[가-힣]+$/;
        if(target.value == ""){
            result.innerHTML = "이름을 입력하세요!";
            varcheck = false;
        }
        else if(!exp_name.test(username.value)){
            result.innerHTML = "이름에는 한글만 입력하세요!";
            username.value = "";
            username.focus();
            varcheck = false;
        }
        else{
            result.innerHTML = "";
        }
    }
    //위의 분기처리에 들어가지 않았다는 뜻은 결국 모든 유효성 검사를 통과했다는 뜻이나 마찬가지이다.
    else{
        varcheck = true;
    }
}

function sendit(){
    const joinForm = document.joinForm;

    const zipcode = joinForm.zipcode;
    if(zipcode.value == ""){
        alert("주소찾기를 진행해 주세요!");
        findAddr();
        return;
    }
    const addrdetail = joinForm.addrdetail;
    if(addrdetail.value == ""){
        alert("상세주소를 입력해 주세요!");
        addrdetail.focus();
        return;
    }

    let flag = false;
    const hobbies = joinForm.userhobby;
    for(let hobby of hobbies){
        if(hobby.checked){
            flag = true;
            break;
        }
    }
    if(!flag){
        result.innerHTML = "취미는 적어도 1개 이상 선택해 주세요!";
        return;
    }
    else{
        result.innerHTML = "";
    }
    //위의 유효성 검사도 통과했다면
    if(varcheck){
        //제출
        joinForm.submit();
    }
}
function pwcheck(){
    const userpw = document.joinForm.userpw;
    const userpw_re = document.joinForm.userpw_re;
    const pwtxt = document.getElementById("pwtxt");

    if(userpw.value != userpw_re.value){
        pwtxt.innerHTML = "이런! 오타를 내셨군요!";
    }
    else{
        pwtxt.innerHTML = "비밀번호가 확인되었어요~"
    }
}
function findAddr() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("addretc").value = extraAddr;
            
            } else {
                document.getElementById("addretc").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("addr").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addrdetail").focus();
        }
    }).open();
}