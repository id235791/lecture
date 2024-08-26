import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import Header from "../../layout/Header";
import Button from "../../components/Button";
import Pagination from "../../components/Pagination";

const Get = () => {
    const {boardnum} = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const cri = location.state;
    const [loginUser,setLoginUser] = useState(null);
    const [nowPage, setNowPage] = useState(1);
    const prevPageRef = useRef(1);
    const replyEndRef = useRef(null);
    const [data,setData] = useState({board:{},files:[]});
    const {board,files} = data;
    const [list,setList] = useState([]);
    const [replyCnt, setReplyCnt] = useState(0);
    const remove = () => {
        axios.delete(`/api/board/${boardnum}`)
        .then(resp=>{
            alert(`${resp.data}번 게시글 삭제!`)
            navigate(`/board/list/`,{state:cri})
        })
    }
    const regist = (e) => {
        const replyForm = document.getElementsByClassName("replyForm")[0];
        const replycontents = document.getElementById("replycontents");
        console.log(replyForm, e.target)
        replyForm.style.display = 'flex';
        e.target.style.display = 'none';
        replycontents.focus();
    }
    const finish = (e) => {
        const replycontents = document.getElementById("replycontents");
        const value = replycontents.value;
        if(value == ""){
            alert("댓글 내용을 입력하세요!")
            replycontents.focus();
            return;
        }
        const reply = {replycontents:value, userid:loginUser, boardnum:boardnum};
        axios.post("/api/reply/regist",reply)
        .then(resp=>{
            alert(`${resp.data.replynum}번 댓글 등록 완료!`)
            if(list.length == 5){
                setNowPage(nowPage+1);
            }
            else{
                setList([...list,reply]);
            }
            replycontents.value = "";
            document.querySelector(".btn.regist").style.display = "inline-block";
            const replyForm = document.getElementsByClassName("replyForm")[0];
            replyForm.style.display = 'none';
        })
    }
    const cancel = () => {
        const replycontents = document.getElementById("replycontents");
        replycontents.value = "";
        document.querySelector(".btn.regist").style.display = "inline-block";
        const replyForm = document.getElementsByClassName("replyForm")[0];
        replyForm.style.display = 'none';
    }

    const modifyReply = (e,replynum) => {
        const replyTag = document.querySelector(`.reply${replynum}`);
        replyTag.innerHTML = `<textarea class='${replynum} mdf'>${replyTag.innerHTML.trim()}</textarea>`;
        e.target.classList.add('hdd');
        e.target.nextElementSibling.classList.remove('hdd');
        document.querySelector(`.mdf`).focus();
    }
    const modifyReplyOk = (e,replynum) => {
        const replycontents = document.querySelector(`.mdf`);
        if(replycontents.value == ""){
            alert("수정할 댓글 내용을 입력하세요!");
            replycontents.focus();
            return;
        }
        const reply = {replynum:replynum, replycontents:replycontents.value, userid:loginUser}
        axios.put(`/api/reply/${replynum}`,reply)
        .then(resp=>{
            alert(`${replynum}번 댓글 수정 완료!`);
            const updated = []
            for(let i=0;i<list.length;i++){
                if(list[i].replynum == replynum){
                    updated.push(reply);
                }
                else{
                    updated.push(list[i])
                }
            }
            setList(updated);
        })
    }
    const removeReply = (e,replynum) => {
        axios.delete(`/api/reply/${replynum}`)
        .then(resp=>{
            alert(`${replynum}번 댓글 삭제 완료!`)
            const updated = list.filter(reply=> reply.replynum !== replynum);
            setList(updated);

            if(updated.length == 0 && nowPage > 1){
                setNowPage(nowPage-1);
            }
        })
    }
    const changePage = (e) => {
        e.preventDefault();
        const page = e.target.getAttribute("href");
        setNowPage(page);

    }
    useEffect(() => {
        axios.get(`/api/user/loginCheck`)
            .then(resp => { setLoginUser(resp.data);})
            .catch(e => { setLoginUser(""); });
        axios.get(`/api/board/${boardnum}`)
            .then(resp => {
                setData(resp.data);
            })
    }, []);

    useEffect(()=>{
        axios.get(`/api/reply/list/${boardnum}/${nowPage}`)
        .then(resp => {
            setList(resp.data.list);
            setReplyCnt(resp.data.replyCnt)
        })
    },[nowPage])
    useEffect(()=>{
        if(prevPageRef.current !== nowPage){
            replyEndRef.current?.scrollIntoView({behavior:'smooth'})
            prevPageRef.current = nowPage;
        }
    },[list])

    if (loginUser === null) {
        return <>로딩중...</>;
    } else {
        const el = [];
        let endPage = Math.ceil(nowPage/5)*5;
        let startPage = endPage-4;
        endPage = (endPage-1)*5 >= replyCnt ? 
            Math.ceil(replyCnt/5) : endPage;
        let prev = startPage != 1;
        let next = endPage*5 < replyCnt;

        const paging = [];
        if(prev){
            paging.push(<a className="changePage page-btn" href={startPage-1} key={startPage-1} onClick={changePage}>&lt;</a>)
        }
        for(let i=startPage;i<=endPage;i++){
            if(i == nowPage){
                paging.push( <span className="nowPage" key={i}>{i}</span> );
            }
            else{
                paging.push( <a href={i} className="changePage page-btn" key={i} onClick={changePage}>{i}</a> )
            }
        }
        if(next){
            paging.push(<a href={endPage+1} className="changePage page-btn" key={endPage+1} onClick={changePage}>&gt;</a>)
        }

        if(list == null || list.length == 0){
            if(nowPage == 1){
                el.push(<li className="no-reply" key={`li${0}`}>등록된 댓글이 없습니다.</li>)
            }else{
                setNowPage(nowPage-1);
            }
        }

        for(let i=0;i<list.length;i++){
            const reply = list[i];
            el.push(
                <li className={`li${reply.replynum} row`} key={`li${reply.replynum}`}>
                    <div className="row">
                        <strong className={`userid${reply.userid}`}>{reply.userid}</strong>
                        <p className={`reply${reply.replynum}`}>{reply.replycontents}</p>
                    </div>
                    <div>
                        <strong></strong>
                    </div>
                    <div>
                        {
                            reply.userid == loginUser?<>
                                <Button value="수정" className="modify btn" onClick={(e)=>{modifyReply(e,reply.replynum)}}></Button>
                                <Button value="수정 완료" className="mfinish btn hdd" onClick={(e)=>{ modifyReplyOk(e,reply.replynum)}}></Button>
                                <Button value="삭제" className="remove btn" onClick={(e)=>{removeReply(e,reply.replynum)}}></Button>
                            </>
                            :''
                        }
                    </div>
                </li>
            )
        }

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
                <div className="reply_line">
                    <input type="button" value="댓글 등록" className="btn regist" onClick={regist}/>
                    <div className="replyForm row" style={{display:"none"}}>
                        <div>
                            <h4>작성자</h4>
                            <input type="text" name="userid" value={loginUser} readOnly/>
                        </div>
                        <div>
                            <h4>내 용</h4>
                            <textarea name="replycontents" id="replycontents" placeholder="Contents"></textarea>
                        </div>
                        <div>
                            <input type="button" value="등록" className="btn finish" onClick={finish}/>
                            <input type="button" value="취소" className="btn cancel" onClick={cancel}/>
                        </div>
                    </div>
                    <ul className="replies">
                        {
                            el
                        }
                    </ul>
                    <div className="page">
                    {
                        paging
                    }
                    </div>
                    <div ref={replyEndRef}></div>
                </div>
            </div>
        );
    }
}
export default Get;