import { useEffect, useState } from "react";
import Button from "../../components/Button";
import Header from "../../layout/Header";
import axios from "axios";
import Pagination from "../../components/Pagination";
import Dropdown from "../../components/Dropdown";
import { useLocation, useNavigate } from "react-router-dom";

const List = () => {
    // :pagenum 으로 보낸 파라미터 수집
    const [pagenum,setPagenum] = useState('1');
    // 세션 아이디 state
    const [loginUser,setLoginUser] = useState();
    const navigate = useNavigate();
    const location = useLocation();
    // 외부에서 받아온 cri가 있다면 받기
    const cri = location.state;
    // 렌더링 할 "게시글 리스트" 리액트 엘리먼트를 추가해주기 위한 배열
    const elList = [];
    // 백 서버에서 받아올 데이터(변하면 리렌더링 해야 함)
    const [data, setData] = useState();
    // 렌더링 하기 위한 페이지 구성 조건(변하면 리렌더링 해야 함)
    const [pageMaker, setPagemaker] = useState({
        startPage:1,
        endPage:1,
        realEnd:1,
        total:0,
        prev:false,
        next:false,
        cri:cri||{
            pagenum:1,
            amount:10,
            type:"a",
            keyword:"",
            startrow:0
        }
    });
    // 검색어를 입력할 인풋창의 데이터 상태
    const [inputs,setInputs] = useState('');

    // 드롭다운 클릭하여 선택했을 때 수행할 이벤트 핸들러
    const changeType = (value)=>{
        // 밖에서 선택한 메뉴의 value를 전달받아서
        const cri = pageMaker.cri;
        // 현재 페이지 구성의 조건(cri)의 type을 그것으로 변경
        cri.type = value;
        // pageMaker를 변화시켜서 리렌더링 시도
        setPagemaker(
            {...pageMaker,["cri"]:cri}
        );
    }
    const inputKeyword = (e)=>{
        setInputs(e.target.value);
    }
    const clickSearch = (e) => {
        const cri = pageMaker.cri;
        // 현재 페이지 구성의 조건(cri)의 type과 keyword를 검색 조건과 키워드로 변경하여
        cri.type = document.getElementById("type").value;
        cri.keyword = document.getElementById("keyword").value;
        // state를 변화시키며 리렌더링 시도
        setPagemaker(
            {...pageMaker,["cri"]:cri}
        );
    }

    useEffect(()=>{
        // 로그인 체크
        axios.get(`/api/user/loginCheck`).then(resp=>{ setLoginUser(resp.data) }).catch(setLoginUser(""));
        // 백 서버에서 데이터 받아오기
        axios.get(`/api/board/list/${pagenum}`,{params:pageMaker.cri})
        .then(resp=>{
            // 렌더링을 위한 데이터 세팅과
            setData(resp.data);
            // 검색을 시도한 상태라면 input창에 그 keyword가 남아있도록 세팅
            setInputs(resp.data.pageMaker.cri.keyword)
        })
    },[pageMaker])
    useEffect(()=>{
        // 만약 이전 페이지에서 cri를 받아온 것이 있다면
        if(location.state){
            // pageMaker의 cri를 그 받아온 것으로 세팅
            setPagemaker({...pageMaker,["cri"]:location.state});
        }
    },[location.state])
    if(!data){
        return <>로딩중...</>
    }
    else{
        const list = data.list;
        const replyCntList = data.replyCntList;
        if(list && list.length > 0){
            const len = list.length;
            for(let i=0;i<len;i++){
                const board = list[i];
                elList.push(
                    <div className="row" key={board.boardnum} onClick={()=>{ navigate(`/board/${board.boardnum}`,{state:pageMaker.cri}) }}>
                        <div>{board.boardnum}</div>
                        <div>
                            {board.hotBoard?<sup className="hot">Hot</sup>:""}
                            {board.newBoard?<sup className="new">New</sup>:""}
                            <a className="get">
                                {board.boardtitle}<span id="reply_cnt">[{replyCntList[i]}]</span>
                            </a>
                        </div>
                        <div>{board.userid }</div>
                        <div>{board.regdate }{board.regdate != board.updatedate?"(수정됨)":""}</div>
                        <div>{board.readcount }</div>
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

        // 드롭다운을 구성하기 위한 재료 객체
        const searchType = {"전체":"a","제목":"T","내용":"C","작성자":"W","제목 또는 내용":"TC","제목 또는 작성자":"TW","제목 또는 내용 또는 작성자":"TCW"}
        return (
            <>
                <div id="wrap" className="list">
                    <Header loginUser={loginUser}></Header>
                    <div className="tar w1000 board-cnt">글 개수 : 0</div>
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
                                        navigate("/board/write",{state:pageMaker.cri})
                                    }}>글쓰기</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div className="search_area">
                        <form name="searchForm" id="searchForm" action="/board/list" className="row">
                            <Dropdown name={"type"} list={searchType} width={250} value={pageMaker.cri.type} onChange={changeType}></Dropdown>
                            <input type="search" id="keyword" name="keyword" value={inputs} onChange={inputKeyword}/>
                            <a id="search-btn" className="btn" onClick={clickSearch}>검색</a>
                            <input type="hidden" name="pagenum" value={pageMaker.cri.pagenum}/>
                            <input type="hidden" name="amount" value={pageMaker.cri.amount}/>
                        </form>
                    </div>
                </div>
            </>
        )
    }
}
export default List;