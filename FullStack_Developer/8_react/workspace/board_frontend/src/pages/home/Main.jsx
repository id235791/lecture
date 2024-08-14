import { useEffect, useRef, useState } from "react";
import { useLocation } from "react-router";
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';

import Button from '../../components/Button'

const Main = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const [inputs,setInputs] = useState({userid:"",userpw:""})
    const {userid,userpw} = inputs;

    const inputRef = useRef([]);
    const addInputRef = (el) => {
        if(!inputRef.current.includes(el)){
            inputRef.current.push(el);
        }
    }
    useEffect(()=>{
        axios.get("/api/user/joinCheck").then(resp=>{
            const joinid = resp.data;
            setInputs({...inputs, userid:joinid});
            
        })
        window.setTimeout(function(){
            document.querySelector(`#wrap+img`).classList = "hdd";
            document.querySelector(`#wrap`).style.display = "block";
            if(inputRef.current[0].value != ""){
                inputRef.current[1].focus();
            }else{
                inputRef.current[0].focus();
            }
        },1300)
    },[location])
    const change = (e) => {
        const {name,value} = e.target;
        setInputs({
            ...inputs,
            [name]:value,
        })
    }

    const login = () => {
        if(!userid){
            alert("아이디를 입력해주세요");
            inputRef.current[0].focus();
            return;
        }
        else if(!userpw){
            alert("비밀번호를 입력해주세요")
            inputRef.current[1].focus();
            return;
        }
        const user = {userid,userpw};
        axios.get("/api/user/login",{params:user})
        .then(resp=>{
            if(resp.data.trim() == "O"){
                alert(`${userid}님 환영합니다!`);
                navigate("/board/list")
            }
        })
        setInputs({userid:"",userpw:""})
    }
    return (
        <div className="main">
            <div id="wrap" className="login">
                <form action="/user/login" method="post" name="loginForm">
                    <table>
                        <tbody>
                            <tr>
                                <th>아이디</th>
                                <td>
                                    <input type="text" name="userid" value={inputs.userid} ref={addInputRef} onChange={change}/>
                                </td>
                            </tr>
                            <tr>
                                <th>비밀번호</th>
                                <td>
                                    <input type="password" name="userpw" ref={addInputRef} onChange={change}/>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <div className="btn_area">
                                        <Button value="로그인" onClick={login}></Button>
                                        <Button value="회원가입" onClick={()=>{navigate("/user/join")}}></Button>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <img alt="" src={require('../../assets/images/타이틀.png')}/>
        </div>
    )
}
export default Main;