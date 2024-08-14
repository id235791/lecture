import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import Header from "../../layout/Header";
import Button from "../../components/Button";

const Get = () => {
    const {boardnum} = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const cri = location.state;
    const [loginUser,setLoginUser] = useState(null);
    const [data,setData] = useState({board:{},files:[]});
    const {board,files} = data;
    const remove = () => {
        axios.delete(`/api/board/${boardnum}`)
        .then(resp=>{
            alert(`${resp.data}번 게시글 삭제!`)
            navigate(`/board/list/`,{state:cri})
        })
    }
    useEffect(() => {
        axios.get(`/api/user/loginCheck`)
            .then(resp => { setLoginUser(resp.data); })
            .catch(e => { setLoginUser(""); });
        axios.get(`/api/board/${boardnum}`)
            .then(resp => {
                setData(resp.data);
            })
    }, []);
    if (loginUser === null) {
        return <>로딩중...</>;
    } else {
        return (
            <div id="wrap" className="get">
                <Header loginUser={loginUser}></Header>
                <form id="boardForm" name="boardForm">
                    <div className="table">
                        <div className="row">
                            <div>제목</div>
                            <div>
                                <input type="text" name="boardtitle" maxLength={50} placeholder="제목을 입력하세요" defaultValue={board.boardtitle} readOnly/>
                            </div>
                        </div>
                        <div className="row">
                            <div>작성자</div>
                            <div>
                                <input type="text" name="userid" maxLength={50} defaultValue={board.userid} readOnly />
                            </div>
                        </div>
                        <div className="row">
                            <div>내용</div>
                            <div>
                                <textarea name="boardcontents" defaultValue={board.boardcontents} readOnly></textarea>
                            </div>
                        </div>
                        {
                            files.map((file,i) => {
                                let ext = file.systemname.split(".").pop();
                                let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
                                return (
                                    <div className={`row r${i}`} key={`r${i}`}>
                                        <div>첨부 파일{i + 1}</div>
                                        <div className={`file${i}_cont row`}>
                                            <div className="cols-10">
                                                <a className="download" id={`file${i}name`} href={`/api/file/download/${file.systemname}`}>{file.orgname}</a>
                                            </div>
                                                <div className="thumbnail_area">
                                                    {
                                                        isThumbnail?
                                                        <img src={`/api/file/thumbnail/${file.systemname}`} alt={`thumbnail${i}`} className="thumbnail" />
                                                        :""
                                                    }
                                                </div>
                                        </div>
                                    </div>
                                )
                            })
                        }
                    </div>
                </form>
                <table className="btn_area">
                    <tbody>
                        <tr>
                            <td>
                                {loginUser == board.userid?
                                <>
                                    <Button value="수정" className="btn" onClick={()=>{ navigate(`/board/modify/${board.boardnum}`,{state:cri}) }}></Button>
                                    <Button value="삭제" className="btn" onClick={remove}></Button>
                                </>
                                :""
                                }
                                <Button value="목록" className="btn" onClick={() => {
                                    navigate("/board/list", { state: cri });
                                }}></Button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}
export default Get;