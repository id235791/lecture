import axios from "axios";
import { useEffect, useState } from "react";
import Button from "../../components/Button";
import DaumPostcode from "../../components/DaumPostCode";

const MyPage = () => {
    const [user,setUser] = useState(null);
    let pwTest = [false,false,false,false,false]
    const pwCheck = (e) => {
        const userpw = document.joinForm.userpw;
        const userpw_re = document.joinForm.userpw_re;
        const pw_check = document.getElementById(`pw_check`);
        const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@-]).{4,}$/;
        const c = document.querySelectorAll(`.pw_check span`);
        console.log(pwTest);
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

    const [userhobby,setUserhobby] = useState([]);
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
        const updated = userhobby;
        for(let i in updated){
            if(updated[i] == deleteHobby){
                setUserhobby(updated.splice(i,1));
                break;
            }
        }
        e.target.parentElement.remove();
    }

    useEffect(()=>{
        axios.get(`/api/user/getDetail`)
        .then(resp => {
            setUser(resp.data);
            setUserhobby(resp.data.userhobby.split("\\"));
        })
    },[])
    if(!user){
        return <>로딩중...</>
    }
    else{
        return(
            <>
                <div id="wrap" className="mypage">
                    <form action="/user/join" method="post" name="joinForm">
                        <table>
                            <tbody>
                                <tr>
                                    <th><label htmlFor="userid">아이디</label></th>
                                    <td>
                                        <input type="text" name="userid" id="userid" readOnly defaultValue={user.userid} className="lock"/>
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
                                    <td><input type="text" name="username" id="username" defaultValue={user.username} className="lock"/></td>
                                </tr>
                                <tr className="gender_area">
                                    <th>성별</th>
                                    <td>
                                        <div>
                                            <ul>
                                                <li className="radio_item">
                                                    <input type="radio" id="usergender1" name="usergender" value="M" checked={user.usergender[0] == 'M'} readOnly/><label htmlFor="usergender1">남자</label>
                                                </li>
                                                <li className="radio_item">
                                                    <input type="radio" id="usergender2" name="usergender" value="W" checked={user.usergender[0] == 'W'} readOnly/><label htmlFor="usergender2">여자</label>
                                                </li>
                                            </ul>
                                            <ul>
                                                <li className="radio_item">
                                                    <input type="radio" id="foreigner1" name="foreigner" value="K" checked={user.usergender[2] == 'K'} readOnly/><label htmlFor="foreigner1">내국인</label>
                                                </li>
                                                <li className="radio_item">
                                                    <input type="radio" id="foreigner2" name="foreigner" value="F" checked={user.usergender[2] == 'F'} readOnly/><label htmlFor="foreigner2">외국인</label>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <tr className="zipcode_area">
                                    <th>우편번호</th>
                                    <td>
                                        <input type="text" name="zipcode" placeholder="우편번호" id="zipcode" readOnly defaultValue={user.zipcode}/>
                                        <DaumPostcode></DaumPostcode>
                                    </td>
                                </tr>
                                <tr className="addr_area">
                                    <th>주소</th>
                                    <td>
                                        <input type="text" name="addr" id="addr" placeholder="주소" readOnly defaultValue={user.addr}/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>상세주소</th>
                                    <td>
                                        <input type="text" name="addrdetail" id="addrdetail" placeholder="상세주소" defaultValue={user.addrdetail}/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>참고항목</th>
                                    <td>
                                        <input type="text" name="addretc" id="addretc" placeholder="참고항목" readOnly defaultValue={user.addretc}/>
                                    </td>
                                </tr>
                                <tr className="hobby_area">
                                    <th>취미</th>
                                    <td>
                                        <div>
                                            <div className="hobby_input">
                                                <input type="text" name="hobby" onKeyUp={hobbyKeyUp}/><Button value="추가" onClick={addHobby}></Button>
                                            </div>
                                            <div className="hobby_list"></div>
                                            <input type="hidden" defaultValue={user.userhobby} name="userhobby"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th colSpan="2">
                                        <Button value="수정 완료" onClick={()=>{}}></Button>
                                        <Button value="취소" onClick={()=>{}}></Button>
                                    </th>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </>
        )
    }
}
export default MyPage;