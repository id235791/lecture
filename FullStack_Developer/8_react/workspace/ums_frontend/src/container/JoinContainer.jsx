import { useEffect, useState } from "react";
import Join from "../components/Join"

const JoinContainer = () => {
    const [inputs,setInputs] = useState({userid:"",userpw:""})
    const {userid,userpw} = inputs;
    let pwTest = [false,false,false,false,false]
    const userhobby = [];
    const checkId = (e) => {
        e.preventDefault();
        const result = document.getElementById("result");
        const userid = document.joinForm.userid;
        if(userid.value == ""){
            alert("아이디를 입력하세요!")
            userid.focus();
            return false;
        }

        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(xhr.readyState == XMLHttpRequest.DONE){
                if(xhr.status == 200){
                    result.innerHTML = "사용할 수 있는 아이디입니다!";
                    document.joinForm.userpw.focus();
                }
                else if(xhr.status == 409){
                    result.innerHTML = "중복된 아이디가 있습니다!";
                    userid.value = "";
                    userid.focus();
                }
            }
        }

        xhr.open("GET",`/user/checkId?userid=${userid.value}`)
        xhr.send();
    }
    const pwCheck = (e) => {
        const userpw = document.joinForm.userpw;
        const userpw_re = document.joinForm.userpw_re;
        const pw_check = document.getElementById("pw_check");
        const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@-]).{4,}$/;
        const c = document.querySelectorAll(".pw_check span");
        console.log("비밀번호 : "+/^[a-zA-Z0-9~?!@-]*$/.test(userpw.value));
        if(userpw.value.length == 0){
            for(let i=0;i<5;i++){
                pwTest[i] = false;
                c[i].classList = "";
            }
            return;
        }
        if(!reg.test(userpw.value)){
            c[0].classList = "pcf";
            pwTest[0] = false;
        }
        else{
            c[0].classList = "pct";
            pwTest[0] = true;
        }
        if(userpw.value.length < 8){
            c[1].classList = "pcf";
            pwTest[1] = false;
        }
        else{
            c[1].classList = "pct";
            pwTest[1] = true;
        }
        if(/(\w)\1\1\1/.test(userpw.value) || /(\s)\1\1\1/.test(userpw.value)){
            c[2].classList = "pcf";
            pwTest[2] = false;
        }
        else{
            c[2].classList = "pct";
            pwTest[2] = true;
        }
        if(!/^[a-zA-Z0-9~?!@-]*$/.test(userpw.value)){
            c[3].classList = "pcf";
            pwTest[3] = false;
        }
        else{
            c[3].classList = "pct";
            pwTest[3] = true;
        }
        if(userpw.value != userpw_re.value){
            c[4].classList = "pcf";
            pwTest[4] = false;
        }
        else{
            c[4].classList = "pct";
            pwTest[4] = true;
        }
    }

    let flag1=true;
    let flag2=true;
    const addHobby = (e) => {
        flag2 = false;
        if(e){
            e.preventDefault();
        }
        const joinForm = document.joinForm;
        const hobby_list = document.getElementsByClassName("hobby_list")[0];
        let hobby = joinForm.hobby;
        if(hobby.value == ""){
            alert("취미를 입력해 주세요.")
            hobby.focus();
            return;
        }
        if(userhobby.indexOf(hobby.value) != -1){
            alert("중복된 취미입니다.")
            hobby.focus();
            hobby.value = "";
            return;
        }
        if(!/^[가-힣a-zA-Z\s]+$/.test(hobby.value)){
            alert("정확한 취미를 입력해 주세요.")
            hobby.focus();
            hobby.value = "";
            return;
        }
        if(userhobby.length >= 5){
            alert("취미는 5개 이하로 입력해 주세요.")
            return;
        }
        const inputHobby = document.createElement("span");
        inputHobby.classList = "userhobby";
        inputHobby.name = "userhobby";
        inputHobby.innerHTML = "<span>"+hobby.value+"</span>";
        userhobby.push(hobby.value);
        
        const xBox = document.createElement("a")
        xBox.classList = "xBox";
        xBox.addEventListener('click',function(e){
            let deleteHobby = e.target.parentElement.innerText;
            for(let i in userhobby){
                if(userhobby[i] == deleteHobby){
                    userhobby.splice(i,1);
                    break;
                }
            }
            e.target.parentElement.remove();
        })
        inputHobby.appendChild(xBox);
        
        hobby_list.appendChild(inputHobby);
        
        const hobbies = document.querySelectorAll(".userhobby>span");
        for(let i=0;i<hobbies.length;i++){
            hobbies[i].addEventListener('click',deleteHobby)
        }
        
        hobby.value="";
        hobby.focus();
    }
    const hobbyKeyUp = (e) => {
        console.log(flag1, flag2);
        if(e.key === "Enter"){
            if(flag1 && flag2){
                flag1 = false;
                addHobby();
            }
        }
        flag1 = true;
        flag2 = true;
    }

    const deleteHobby = (e) => {
        let deleteHobby = e.target.innerText;
        for(let i in userhobby){
            if(userhobby[i] == deleteHobby){
                userhobby.splice(i,1);
                break;
            }
        }
        e.target.parentElement.remove();
    }
    const clickJoin = (e) => {
        e.preventDefault();
        const joinForm = document.joinForm;
    
        const userid = joinForm.userid;
        if(userid.value == ""){
            alert("아이디를 입력하세요!")
            userid.focus();
            return false;
        }
        if(userid.value.length < 5 || userid.value.length > 12){
            alert("아이디는 5자 이상 12자 이하로 입력하세요!");
            userid.focus();
            return false;
        }
        
        const result = document.getElementById("result");
        if(result.innerHTML == ""){
            alert("아이디 중복검사를 진행해주세요!");
            userid.focus();
            return false;
        }
        if(result.innerHTML == "중복된 아이디가 있습니다!"){
            alert("중복체크 통과 후 가입이 가능합니다!");
            userid.focus();
            return false;
        }
        
        const userpw = joinForm.userpw;
        for(let i=0;i<5;i++){
            if(!pwTest[i]){
                alert("비밀번호 확인을 다시하세요!");
                userpw.focus();
                return false;
            }
        }

        const username = joinForm.username;
        if(username.value == ""){
            alert("이름을 입력하세요!");
            username.focus();
            return false;
        }

        const exp_name = /[가-힣]+$/;
        if(!exp_name.test(username.value)){
            alert("이름에는 한글만 입력하세요!");
            username.focus();
            return false;
        }

        const usergender = joinForm.usergender;
        if(!usergender[0].checked && !usergender[1].checked){
            alert("성별을 선택하세요!");
            return false;
        }
        
        const foreigner = joinForm.foreigner;
        if(!foreigner[0].checked && !foreigner[1].checked){
            alert("국적을 선택하세요!");
            return false;
        }
        
        const zipcode = joinForm.zipcode;
        if(zipcode.value == ""){
            alert("주소찾기를 진행해주세요!");
            return false;
        }

        const addrdetail = joinForm.addrdetail;
        if(addrdetail.value == ""){
            alert("나머지 주소를 입력해주세요.")
            addrdetail.focus();
            return false;
        }
        console.log(userhobby);
        if(userhobby.length == 0){
            alert("취미는 적어도 1개 이상 입력해 주세요!");
            joinForm.hobby.focus();
            return false;
        }

        const hobbyTag = joinForm.userhobby;
        hobbyTag.value = userhobby.join("\\");

        const user = {
            "userid":userid.value,
            "userpw":userpw.value,
            "username":username.value,
            "usergender":usergender.value+"-"+foreigner.value,
            "zipcode":zipcode.value,
            "addr":joinForm.addr.value,
            "addrdetail":addrdetail.value,
            "addretc":joinForm.addretc.value,
            "userhobby":hobbyTag.value
        }
        console.log(user);

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
                let result = xhr.responseText.trim();
                if(result == "X"){
                    alert("회원가입 실패!");
                    document.joinForm.userid.focus();
                }
                else{
                    alert("회원가입 성공!")
                    document.location.replace("/");
                }
            }
        }

        xhr.open("POST","/user/join")
        xhr.setRequestHeader("Content-Type","application/json");
        xhr.send(JSON.stringify(user));
    }

    useEffect(()=>{
        document.body.className = "user";
    },[])

    return (
        <>
            <Join
                checkId={checkId}
                pwCheck={pwCheck}
                addHobby={addHobby}
                clickJoin={clickJoin}
                hobbyKeyUp={hobbyKeyUp}
            />
        </>
    )
}
export default JoinContainer;