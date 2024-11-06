import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import Button from "../../components/Button";
import DaumPostcode from "../../components/DaumPostCode";
import Hobby from "../../components/Hobby";
import Header from "../../layout/Header";

const MyPage = () => {
    const navigate = useNavigate();
    const [user,setUser] = useState(null);
    let pwTest = [true,true,true,true,true]
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
    
    const [userhobby,setUserhobby] = useState([]);

    const modify = () => {
        const joinForm = document.joinForm;
    
        const userpw = joinForm.userpw;
        for(let i=0;i<5;i++){
            if(!pwTest[i]){
                alert("비밀번호 확인을 다시하세요!");
                userpw.focus();
                return false;
            }
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
        if(userhobby.value.split("\\").length == 0){
            alert("취미는 적어도 1개 이상 입력해 주세요!");
            joinForm.hobby.focus();
            return false;
        }
        const user = {
            "userpw":userpw.value,
            "zipcode":zipcode.value,
            "addr":joinForm.addr.value,
            "addrdetail":addrdetail.value,
            "addretc":joinForm.addretc.value,
            "userhobby":userhobby.value
        }

        axios.put('/api/user/modify',user)
        .then(resp =>{
            if(resp.data == "O"){
                alert("회원정보 수정 성공!")
                navigate("/board/list");
            }
            else{
                alert("회원정보 수정 실패!");
                navigate("/board/list")
            }
        })
    }

    const leaveId = () => {
        axios.delete("/api/usre/leaveId")
        .then(resp=>{
            alert("안녕히 가세요...☆");
            navigate("/")
        })
    }

    useEffect(()=>{
        axios.get(`/api/user/getDetail`)
        .then(resp => {
            setUser(resp.data);
            if(resp.data){
                setUserhobby(resp.data.userhobby.split("\\"));
            }
        })
    },[])
    const el = [];
    if(!user){
        el.push(<div key={-1}>로딩중...</div>);
    }
    else{
        el.push(
            <form action="/user/join" method="post" name="joinForm" key={1}>
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
                            <td><input type="password" name="userpw" id="userpw" onChange={pwCheck} defaultValue={user.userpw}/></td>
                        </tr>
                        <tr>
                            <th><label htmlFor="userpw_re">비밀번호 확인</label></th>
                            <td><input type="password" name="userpw_re" id="userpw_re" onChange={pwCheck} defaultValue={user.userpw}/></td>
                        </tr>
                        <tr>
                            <th colSpan="2">
                                <div className="pw_check">
                                    <span className="pct">영어 대문자, 소문자, 숫자, 특수문자(~,?,!,@,-)를 모두 하나 이상 포함해야 해요 😀</span>
                                    <span className="pct">최소 8자 이상의 비밀번호가 보안에 안전해요 😄</span>
                                    <span className="pct">같은 문자가 연속해서 사용되지 않았어요 😆</span>
                                    <span className="pct">사용할 수 없는 문자가 포함되지 않았어요 😊</span>
                                    <span className="pct">비밀번호 확인이 완료되었어요! 🤗</span>
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
                                <Hobby data={user.userhobby.split("\\")}></Hobby>
                            </td>
                        </tr>
                        <tr>
                            <th colSpan="2">
                                <Button value="수정 완료" onClick={modify}></Button>
                                <Button value="취소" onClick={()=>{navigate(`/board/list/`)}}></Button>
                                <Button value="회원탈퇴" onClick={leaveId}></Button>
                            </th>
                        </tr>
                    </tbody>
                </table>
            </form>
        )
    }
    return(
        <>
            <div id="wrap" className="mypage">
                <Header></Header>
                {el}
            </div>
        </>
    )
}
export default MyPage;