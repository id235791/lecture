function sendit(){
    const joinForm = document.joinForm;
    const userid = joinForm.userid;
    if(userid.value == ""){
        alert("아이디를 입력하세요!");
        userid.focus();
        return;
    }
    if(userid.value.length<5 || userid.value.length > 12){
        alert("아이디는 5자 이상 12자 이하로 입력하세요!");
        userid.focus();
        userid.value="";
        return;
    }

    const userpw = joinForm.userpw;
    const userpw_re = joinForm.userpw_re;
    const pwtxt = document.getElementById("pwtxt");
    if(userpw.value == ""){
        alert("비밀번호를 입력하세요!");
        userpw.focus();
        return;
    }
    if(userpw.value.length < 8){
        pwtxt.innerHTML = "비밀번호는 8자 이상으로 입력하세요!";
        userpw.value = "";
        userpw.focus();
        return;
    }
    if(pwtxt.innerHTML != "비밀번호가 확인되었어요~"){
        alert("비밀번호 확인을 다시 해주세요!");
        userpw.focus();
        return;
    }

    const username = joinForm.username;
    if(username.value == ""){
        alert("이름을 입력하세요!");
        username.focus();
        return;
    }
    const exp_name = /^[가-힣]+$/;
    if(!exp_name.test(username.value)){
        alert("이름에는 한글만 입력하세요!");
        username.value = "";
        username.focus();
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
        alert("취미는 적어도 1개 이상 선택해 주세요!");
        return;
    }
    joinForm.submit();
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