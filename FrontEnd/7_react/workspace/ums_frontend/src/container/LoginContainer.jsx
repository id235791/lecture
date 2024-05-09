import { useEffect, useState } from "react"
import axios from 'axios';
import Login from '../components/Login'

const LoginContainer = () => {
    const [inputs,setInputs] = useState({userid:"",userpw:""})
    const {userid,userpw} = inputs;

    const change = (e) => {
        const {name,value} = e.target;
        setInputs({
            ...inputs,
            [name]:value
        })
    }

    const clickLogin = (e) => {
        e.preventDefault();
        const loginForm = document.loginForm;
        if(!userid){
            alert("아이디를 입력해주세요!");
            loginForm.userid.focus();
            return;
        }
        if(!userpw){
            alert("비밀번호를 입력해주세요!");
            loginForm.userpw.focus();
            return;
        }
        const user = {userid,userpw};
        //backend 서버로 로그인 요청
        //axios.post("/user/login",user)
        //.then(resp => {
        //  할일
        // })
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange =function() {
            if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
                let result = xhr.responseText.trim();
                if(result == "X"){
                    alert("로그인 실패!")
                }
                else{
                    alert("로그인 성공!")
                    document.location.replace("/list");
                }
                setInputs({
                    userid:"",
                    userpw:""
                })
            }
        }   
        xhr.open("POST","/user/login")
        xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xhr.send(`userid=${userid}&userpw=${userpw}`)
    }

    useEffect(()=>{
        document.body.className = "user";
        axios.get("/user/login")
        .then(resp => {
            const data = resp.data;
            let joinid = data.joinid;
            if(joinid){
                setInputs({
                    ...inputs,
                    userid:joinid
                });
                document.getElementById("userpw").focus();
            }
            else{
                document.getElementById("userid").focus();
            }
        })


    },[])

    return (<>
        <Login
            change={change}
            clickLogin={clickLogin}
            userid={userid}
            userpw={userpw}
        />
    </>)
}
export default LoginContainer;