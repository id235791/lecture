import { useEffect, useRef, useState } from "react";
import Button from "../components/Button";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Main = () => {
    const [inputs,setInputs] = useState({userid:"",userpw:""})
    const {userid,userpw} = inputs;
    const navigate = useNavigate();


    const inputRef = useRef([]);
    const addInputRef = (el) => {
        if(!inputRef.current.includes(el)){
            inputRef.current.push(el);
        }
    }

    const change = (e) => {
        const {name,value} = e.target;
        setInputs({
            ...inputs,
            [name]:value,
        })
    }

    const login = () => {
        if(!userid){
            alert("아이디를 입력해주세요!");
            inputRef.current[0].focus();
            return;
        }
        //{userid:현재userid값, userpw:현재userpw값}
        // {userid:userid, userpw:userpw}
        const user = {userid,userpw};
        console.log(user)

        axios.get('/api/user/login',{params:user}).then((resp)=>{
            if(resp.data.trim() == "O"){
                alert(`${userid}님 환영합니다!`);
                navigate("/board/list");
            }
            else{
                setInputs({userid:"",userpw:""})
                inputRef.current[0].focus();
            }
        })
    }

    useEffect(()=>{
        window.setTimeout(function(){
            document.querySelector("#wrap+img").classList = "hdd";
            document.querySelector("#wrap").style.display = "block";

            if(inputRef.current[0].value == ""){
                inputRef.current[0].focus();
            }
            else{
                inputRef.current[1].focus();
            }
        },1300);
        axios.get("/api/user/joinCheck").then((resp)=>{
            const joinid = resp.data;
            setInputs({...inputs, userid:joinid});
        })
    },[])

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
                                    <input type="password" name="userpw" value={inputs.userpw} ref={addInputRef} onChange={change}/>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <div className="btn_area">
                                        <Button value="로그인" onClick={login}></Button>
                                        <Button value="회원가입" onClick={()=>{ navigate("/user/join") }}></Button>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <img alt="" src={require('../assets/images/타이틀.png')}/>
        </div>
    )
}
export default Main;