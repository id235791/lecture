import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import Header from "../../layout/Header";
import Button from "../../components/Button";

const Get = () => {
    const {boardnum} = useParams();
    const cri = useLocation().state;
    console.log(cri);
    const navigate = useNavigate();

    const [data, setData] = useState({board:null, files:null});
    const {board, files} = data;
    const [loginUser, setLoginUser] = useState("");

    const remove = () => {
        axios.delete(`/api/board/${board.boardnum}`)
        .then((resp)=>{
            alert(`${resp.data}번 게시글 삭제!`)
            navigate(`/board/list`,{state:cri});
        })
    }

    const [nowPage,setNowPage] = useState(1);
    const [list,setList] = useState([]);
    const [replyCnt, setReplyCnt] = useState(0);

    const openReplyForm = (e) => {
        e.target.style.display='none';
        e.target.nextElementSibling.style.display = 'flex';
        document.getElementById("replycontents").focus();
    }
    const clickRegist = (e) => {
        const replycontents = document.getElementById("replycontents");
        if(replycontents.value == ""){
            alert("댓글 내용을 입력하세요!");
            replycontents.focus();
            return;
        }
        const reply = {replycontents:replycontents.value, userid:loginUser, boardnum:board.boardnum};
        axios.post(`/api/reply/regist`,reply)
        .then(resp => {
            alert(`${resp.data}번 댓글 등록 완료!`);
            reply.replynum = resp.data;

            if(list.length == 5){
                setNowPage(Math.ceil((replyCnt+1)/5));
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
    const clickCancel = (e) => {
        const replycontents = document.getElementById("replycontents");
        replycontents.value = "";
        document.querySelector(".btn.regist").style.display = "inline-block";
        const replyForm = document.getElementsByClassName("replyForm")[0];
        replyForm.style.display = 'none';
    }

    const modifyReply = (e, replynum) => {
        const replyTag = document.querySelector(`.reply${replynum}`);
        const replycontents = replyTag.innerHTML.trim();
        replyTag.innerHTML = `<textarea class='${replynum} mdf'>${replycontents}</textarea>`
        e.target.classList.add("hdd");
        e.target.nextElementSibling.classList.remove("hdd");
        document.querySelector(".mdf").focus();
    }
    const modifyReplyOk = (e,replynum) => {
        const replycontents = document.querySelector(".mdf");
        if(replycontents.value == ""){
            alert("수정할 댓글 내용을 입력하세요!")
            replycontents.focus();
            return;
        }
        const reply = {replynum:replynum, replycontents:replycontents.value, userid:loginUser};
        axios.put(`/api/reply/${replynum}`,reply)
        .then(resp=>{
            alert(`${resp.data}번 댓글 수정 완료!`);
            const updatedList = list.map((item) => {
                if(item.replynum == replynum){
                    return reply;
                }
                else{
                    return item;
                }
            })
            setList(updatedList);
        })

    }
    const removeReply = (e,replynum) =>{
        axios.delete(`/api/reply/${replynum}`)
        .then(resp => {
            alert(`${resp.data}번 댓글 삭제 완료!`)
            if(list.length == 1 && nowPage != 1){
                setNowPage(nowPage-1);
            }
            else{
                axios.get(`/api/reply/${boardnum}/${nowPage}`)
                .then(resp => {
                    setList(resp.data.list);
                    setReplyCnt(resp.data.replyCnt);
                })
            }
        })
    }

    const replyEndRef = useRef(null);
    const prevPageRef = useRef(1);

    useEffect(()=>{
        axios.get(`/api/board/${boardnum}`)
        .then((resp)=>{
            //resp.data : { board:{BoardDTO}, files:[{},{},..] }
            setData(resp.data);
        })
        axios.get(`/api/user/loginCheck`).then(resp=>{
            if(resp.data.trim() != ""){
                setLoginUser(resp.data.trim());
            }
        })
    },[])
    useEffect(()=>{
        axios.get(`/api/reply/${boardnum}/${nowPage}`)
        .then(resp => {
            setList(resp.data.list);
            setReplyCnt(resp.data.replyCnt);
        })
        
    },[nowPage])

    useEffect(()=>{
        if(prevPageRef.current != nowPage){
            replyEndRef.current?.scrollIntoView({behavior:'smooth'});
            prevPageRef.current = nowPage;
        }
    },[list])

    if(!board){
        return <>로딩중...</>
    }
    else{
        const replyList = [];
        const paging = [];
        let endPage = Math.ceil(nowPage/5)*5;
        let startPage = endPage - 4;
        endPage = (endPage-1)*5 >= replyCnt ? 
                Math.ceil(replyCnt/5) : endPage;
        let prev = startPage != 1;
        let next = endPage*5 < replyCnt;
        const changePage = (e) => {
            e.preventDefault();
            const page = e.target.getAttribute("href");
            setNowPage(page);
        }
        if(prev){
            paging.push(<a className="changePage page-btn" href={startPage-1} key={startPage-1} onClick={changePage}>&lt;</a>)
        }
        for(let i=startPage; i<=endPage; i++) {
            if(i == nowPage){
                paging.push(<span className="nowPage" key={i}>{i}</span>);
            }
            else{
                paging.push(<a href={i} className="changePage page-btn" key={i} onClick={changePage}>{i}</a>)
            }
        }
        if(next){
            paging.push(<a href={endPage+1} className="changePage page-btn" key={endPage+1} onClick={changePage}>&gt;</a>)
        }

        if(list == null || list.length == 0){
            replyList.push(<li className="no-reply" key={`li0`}>등록된 댓글이 없습니다.</li>);
        }
        for(let i=0; i<list.length; i++){
            const reply = list[i];
            replyList.push(
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
                            reply.userid == loginUser?
                            <>
                                <Button value="수정" className={"modify btn"} onClick={(e)=>{ modifyReply(e,reply.replynum) }}></Button>
                                <Button value="수정 완료" className={"mfinish btn hdd"} onClick={(e)=>{ modifyReplyOk(e,reply.replynum) }}></Button>
                                <Button value="삭제" className={"remove btn"} onClick={(e)=>{ removeReply(e,reply.replynum) }}></Button>
                            </>
                            :""
                        }
                    </div>
                </li>
            )
        }


        return (
            <div id="wrap" className="get">
                <Header></Header>
                <form id="boardForm" name="boardForm">
                    <div className="table">
                        <div className="row">
                            <div>제목</div>
                            <div>
                                <input type="text" name="boardtitle" maxLength={50} placeholder="제목을 입력하세요" readOnly value={board.boardtitle}/>
                            </div>
                        </div>
                        <div className="row">
                            <div>작성자</div>
                            <div>
                                <input type="text" name="userid" maxLength={50} readOnly value={board.userid}/>
                            </div>
                        </div>
                        <div className="row">
                            <div>내용</div>
                            <div>
                                <textarea name="boardcontents" readOnly value={board.boardcontents}></textarea>
                            </div>
                        </div>
                        {
                            files.map((file,i) => {
                                //file : 꺼내온 FileDTO 형태의 객체
                                //i : 순번
                                let ext = file.systemname.split(".").pop();
                                let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
                                return (
                                    <div className={`row r${i}`} key={`r${i}`}>
                                        <div>첨부 파일{i+1}</div>
                                        <div className={`file${i}_cont row`}>
                                            <div className="cols-10">
                                                <a className="download" id={`file${i}name`} href={`/api/file/download/${file.systemname}`}>{file.orgname}</a>
                                            </div>
                                            <div className="thumbnail_area">
                                                {
                                                    isThumbnail?
                                                    <img src={`/api/file/thumbnail/${file.systemname}`} alt={`thumbnail${i}`} className="thumbnail"></img>
                                                    :
                                                    ""
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
                                {
                                    loginUser == board.userid?
                                    <>
                                        <Button value="수정" className="btn" onClick={()=>{ navigate(`/board/modify`,{state:{"cri":cri, "boardnum":board.boardnum}})}}></Button>
                                        <Button value="삭제" className="btn" onClick={remove}></Button>
                                    </>
                                    :
                                    ""
                                }
                                <Button value="목록" className="btn" onClick={() => {
                                    navigate("/board/list", { state: cri });
                                }}></Button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div className="reply_line">
                    <input type="button" value="댓글 등록" className="btn regist" onClick={openReplyForm}/>
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
                            <input type="button" value="등록" className="btn finish" onClick={clickRegist}/>
                            <input type="button" value="취소" className="btn cancel" onClick={clickCancel}/>
                        </div>
                    </div>
                    <ul className="replies">
                        {replyList}
                    </ul>
                    <div className="page">
                        {paging}
                    </div>
                    <div ref={replyEndRef}></div>
                </div>
            </div>
        )
    }
}
export default Get;