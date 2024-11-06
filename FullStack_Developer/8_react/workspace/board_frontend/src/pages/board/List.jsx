import axios from "axios";
import { useEffect, useState } from "react"
import Header from "../../layout/Header";
import { useLocation, useNavigate } from "react-router-dom";
import Pagination from "../../components/Pagination";
import Dropdown from "../../components/Dropdown";

const List = () => {
    const navigate = useNavigate();
    
    //외부에서 받아온 cri가 있다면 받기
    const location = useLocation();
    const sendedCri = location.state;

    const [cri,setCri] = useState({
        pagenum:1,
        amount:10,
        type:"a",
        keyword:"",
        startrow:0
    });

    //백 서버에서 받아올 데이터(변하면 리렌더링 해야 함)
    const [data, setData] = useState();
    // 렌더링 하기 위한 페이지 구성 조건(변하면 리렌더링 해야 함)
    const [pageMaker, setPagemaker] = useState({
        startPage:1,
        endPage:1,
        realEnd:1,
        total:0,
        prev:false,
        next:false,
        cri:null
    })

    const [inputs,setInputs] = useState("");
    const inputKeyword = (e) => {
        setInputs(e.target.value)
    }
    const clickSearch = (e) => {
        e.preventDefault();
        const changedCri = {
            ...cri,
            type:document.getElementById("type").value,
            keyword:inputs,
            pagenum:1
        };
        setCri(changedCri);
    }

    useEffect(()=>{
        const temp = {
            pagenum:cri.pagenum,
            amount:cri.amount,
            type:cri.type,
            keyword:cri.keyword,
            startrow:cri.startrow
        }
        axios.get(`/api/board/list/${cri.pagenum}`,{params:cri}).then((resp)=>{
            setData(resp.data);
            setPagemaker(resp.data.pageMaker);
            setInputs(resp.data.pageMaker.cri.keyword);
        })
    },[cri])
    useEffect(()=>{
        //만약 이전 페이지에서 cri를 받아온것이 있다면
        if(location.state){
            //pageMaker의 cri를 그 받아온 것으로 세팅
            //State가 변화했으므로 리렌더링 진행
            // > 위에 있는 pageMaker에 종속되어 있는 Effect 호출
            setCri(location.state);
        }
    },[location.state])

    if(!data){
        return <>로딩중...</>
    }
    else{
        const list = data.list;
        const elList = [];
        if(list && list.length > 0){
            for(const board of list){
                elList.push(
                    <div className="row" key={board.boardnum} onClick={()=>{ navigate(`/board/${board.boardnum}`,{state:cri}) }}>
                        <div>{board.boardnum}</div>
                        <div>
                            {board.hotBoard?<sup className="hot">Hot</sup>:""}
                            {board.newBoard?<sup className="new">New</sup>:""}
                            <a className="get">
                                {board.boardtitle}<span id="reply_cnt">[{board.replyCnt}]</span>
                            </a>
                        </div>
                        <div>{board.userid}</div>
                        <div>{board.regdate}{board.regdate != board.updatedate ? "(수정됨)" : ""}</div>
                        <div>{board.readcount}</div>
                    </div>
                )
            }
        }
        else{
            elList.push(
                <div className="row no-list" key={-1}>
                    <div>등록된 게시글이 없습니다.</div>
                </div>
            )
        }
        const searchType = {"전체":"a", "제목":"T", "내용":"C", "작성자":"W", "제목 또는 내용":"TC", "제목 또는 작성자":"TW", "제목 또는 내용 또는 작성자":"TCW"}
        return (
            <>
                <div id="wrap" className="list">
                    <Header></Header>
                    <div className="tar w1000 board-cnt">글 개수 : {pageMaker.total}</div>
                    <div className="list table">
                        <div className="thead tac">
                            <div className="row">
                                <div>번호</div>
                                <div>제목</div>
                                <div>작성자</div>
                                <div>날짜</div>
                                <div>조회수</div>
                            </div>
                        </div>
                        <div className="tbody">
                            {elList}
                        </div>
                    </div>
                    <Pagination pageMaker={pageMaker}></Pagination>
                    <table className="btn_table">
                        <tbody>
                            <tr>
                                <td>
                                    <a className="write btn" onClick={()=>{
                                        navigate("/board/write",{state:cri})
                                    }}>글쓰기</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div className="search_area">
                        <form name="searchForm" id="searchForm" action="/board/list" className="row">
                            <Dropdown list={searchType} name={"type"} width={250} value={cri.type}></Dropdown>
                            <input type="search" id="keyword" name="keyword" onChange={inputKeyword} value={inputs}/>
                            <a id="search-btn" className="btn" onClick={clickSearch}>검색</a>
                            <input type="hidden" name="pagenum"/>
                            <input type="hidden" name="amount"/>
                        </form>
                    </div>
                </div>
            </>
        )
    }
}
export default List;