import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "../components/Button";

const Header = () => {
    const [loginUser, setLoginUser] = useState("");
    const navigate = useNavigate();

    const clickLogout = () => {
        axios.get(`/api/user/logout`)
        .then(resp => {
            if(resp.data == "O"){
                alert("안녕히가세요!");
                navigate("/");
            }
        })
    }
    const clickMypage = () => {
        navigate("/user/myInfo");
    }
    const goList = () => {
        const cri = {
            pagenum:1,
            amount:10,
            type:"a",
            keyword:"",
            startrow:0
        }
        navigate("/board/list",{state:cri})
    }

    useEffect(()=>{
        axios.get(`/api/user/loginCheck`)
        .then((resp)=>{
            if(resp.data.trim() == ""){
                alert("로그인 후 이용하세요!");
                navigate("/");
            }
            else{
                setLoginUser(resp.data);
            }
        })
    },[])
    return (
        <header>
            <div className="title">
                <h3 onClick={goList}>Board 프로젝트</h3>
            </div>
            <div className="header_area row">
                <div className="cols-8 tar">
                    <span>{loginUser}님 환영합니다</span>
                </div>
                <div className="cols-2 row btn_area">
                    <Button value="로그아웃" id="logout-btn" onClick={clickLogout}></Button>
                    <Button value="마이페이지" id="mypage-btn" onClick={clickMypage}></Button>
                </div>
            </div>
        </header>
    )
}
export default Header;