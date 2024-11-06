import Button from "../../components/Button";
import DaumPostcode from "../../components/DaumPostCode";
import { useNavigate } from "react-router-dom";
import Hobby from "../../components/Hobby";
import axios from 'axios';

const Join = () => {
    const navigate = useNavigate();
    let pwTest = [false,false,false,false,false]
    const checkId = (e) => {
        const result = document.getElementById(`result`);
        const userid = document.joinForm.userid;
        if(userid.value == ""){
            alert("아이디를 입력하세요!")
            userid.focus();
            return false;
        }
        // localhost:8080/api/user/checkId?userid=apple
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(xhr.readyState == XMLHttpRequest.DONE){
                if(xhr.status == 200){
                    let txt = xhr.responseText.trim();
                    if(txt == "O"){
                        //성공
                        result.innerHTML = "사용할 수 있는 아이디입니다!";
                        document.joinForm.userpw.focus();
                    }
                    else{
                        //실패
                        result.innerHTML = "중복된 아이디가 있습니다!";
                        userid.value = "";
                        userid.focus();
                    }
                }
            }
        }
        xhr.open("GET","/api/user/checkId?userid="+userid.value);
        xhr.send();
    }
    const pwCheck = (e) => {
        const userpw = document.joinForm.userpw;
        const userpw_re = document.joinForm.userpw_re;
        const pw_check = document.getElementById(`pw_check`);
        const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@-]).{4,}$/;
        const c = document.querySelectorAll(`.pw_check span`);
        if(userpw.value.length == 0){
            for(let i=0;i<5;i++){
                pwTest[i] = false;
                c[i].classList = "";
            }
            return;
        }
        if(!reg.test(userpw.value)){
            c[0].classList = `pcf`;
            pwTest[0] = false;
        }
        else{
            c[0].classList = `pct`;
            pwTest[0] = true;
        }
        if(userpw.value.length < 8){
            c[1].classList = `pcf`;
            pwTest[1] = false;
        }
        else{
            c[1].classList = `pct`;
            pwTest[1] = true;
        }
        if(/(\w)\1\1\1/.test(userpw.value) || /(\s)\1\1\1/.test(userpw.value)){
            c[2].classList = `pcf`;
            pwTest[2] = false;
        }
        else{
            c[2].classList = `pct`;
            pwTest[2] = true;
        }
        if(!/^[a-zA-Z0-9~?!@-]*$/.test(userpw.value)){
            c[3].classList = `pcf`;
            pwTest[3] = false;
        }
        else{
            c[3].classList = `pct`;
            pwTest[3] = true;
        }
        if(userpw.value != userpw_re.value){
            c[4].classList = `pcf`;
            pwTest[4] = false;
        }
        else{
            c[4].classList = `pct`;
            pwTest[4] = true;
        }
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
        const userhobby = joinForm.userhobby;
        if(userhobby.value.length == 0){
            alert("취미는 적어도 1개 이상 입력해 주세요!");
            joinForm.hobby.focus();
            return false;
        }

        const user = {
            userid:userid.value,
            userpw:userpw.value,
            username:username.value,
            usergender:usergender.value+"-"+foreigner.value,
            zipcode:zipcode.value,
            addr:joinForm.addr.value,
            addrdetail:addrdetail.value,
            addretc:joinForm.addretc.value,
            userhobby:userhobby.value
        }
        console.log(user);

        // const xhr = new XMLHttpRequest();
        // xhr.onreadystatechange = () => {
        //     if(xhr.readyState == XMLHttpRequest.DONE){
        //         if(xhr.status == 200){
        //             alert("회원가입 성공!");
        //             navigate("/");
        //         }
        //         else{
        //             console.log(xhr.responseText);
        //         }
        //     }
        // }

        // xhr.open("POST","/api/user/join");
        // xhr.setRequestHeader("Content-Type","application/json; charset=utf-8")
        // xhr.send(JSON.stringify(user));

        axios.post('/api/user/join',user)
        .then((resp)=>{
            alert("회원가입 성공!");
            navigate("/");
        })
        .catch((err)=>{
            alert("회원가입 실패!");
        })

    }

    return (
        <>
            <div id="wrap" className="join">
                <form action="/user/join" method="post" name="joinForm">
                    <table>
                        <tbody>
                            <tr>
                                <td colSpan="2" id="result"></td>
                            </tr>
                            <tr>
                                <th><label htmlFor="userid">아이디</label></th>
                                <td>
                                    <input type="text" name="userid" id="userid"/>
                                    <Button value="중복검사" onClick={checkId}></Button>
                                </td>
                            </tr>
                            <tr>
                                <th><label htmlFor="userpw">비밀번호</label></th>
                                <td><input type="password" name="userpw" id="userpw" onChange={pwCheck}/></td>
                            </tr>
                            <tr>
                                <th><label htmlFor="userpw_re">비밀번호 확인</label></th>
                                <td><input type="password" name="userpw_re" id="userpw_re" onChange={pwCheck}/></td>
                            </tr>
                            <tr>
                                <th colSpan="2">
                                    <div className="pw_check">
                                        <span>영어 대문자, 소문자, 숫자, 특수문자(~,?,!,@,-)를 모두 하나 이상 포함해야 해요 😀</span>
                                        <span>최소 8자 이상의 비밀번호가 보안에 안전해요 😄</span>
                                        <span>같은 문자가 연속해서 사용되지 않았어요 😆</span>
                                        <span>사용할 수 없는 문자가 포함되지 않았어요 😊</span>
                                        <span>비밀번호 확인이 완료되었어요! 🤗</span>
                                    </div>
                                </th>
                            </tr>
                            <tr>
                                <th><label htmlFor="username">이름</label></th>
                                <td><input type="text" name="username" id="username"/></td>
                            </tr>
                            <tr className="gender_area">
                                <th>성별</th>
                                <td>
                                    <div>
                                        <ul>
                                            <li className="radio_item">
                                                <input type="radio" id="usergender1" name="usergender" value="M"/><label htmlFor="usergender1">남자</label>
                                            </li>
                                            <li className="radio_item">
                                                <input type="radio" id="usergender2" name="usergender" value="W"/><label htmlFor="usergender2">여자</label>
                                            </li>
                                        </ul>
                                        <ul>
                                            <li className="radio_item">
                                                <input type="radio" id="foreigner1" name="foreigner" value="K"/><label htmlFor="foreigner1">내국인</label>
                                            </li>
                                            <li className="radio_item">
                                                <input type="radio" id="foreigner2" name="foreigner" value="F"/><label htmlFor="foreigner2">외국인</label>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr className="zipcode_area">
                                <th>우편번호</th>
                                <td>
                                    <input type="text" name="zipcode" placeholder="우편번호" id="zipcode" readOnly/>
                                    <DaumPostcode></DaumPostcode>
                                </td>
                            </tr>
                            <tr className="addr_area">
                                <th>주소</th>
                                <td>
                                    <input type="text" name="addr" id="addr" placeholder="주소" readOnly/>
                                </td>
                            </tr>
                            <tr>
                                <th>상세주소</th>
                                <td>
                                    <input type="text" name="addrdetail" id="addrdetail" placeholder="상세주소"/>
                                </td>
                            </tr>
                            <tr>
                                <th>참고항목</th>
                                <td>
                                    <input type="text" name="addretc" id="addretc" placeholder="참고항목" readOnly/>
                                </td>
                            </tr>
                            <tr className="hobby_area">
                                <th>취미</th>
                                <td>
                                    <Hobby name={"취미"}></Hobby>
                                </td>
                            </tr>
                            <tr>
                                <th colSpan="2">
                                    <Button value="가입 완료" onClick={clickJoin}></Button>
                                </th>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </>
    );
}
export default Join;