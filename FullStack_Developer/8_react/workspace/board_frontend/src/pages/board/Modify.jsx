import { useLocation, useNavigate, useParams } from "react-router-dom";
import Button from "../../components/Button";
import Header from "../../layout/Header";
import { useEffect, useRef, useState } from "react";
import axios from "axios";

const Modify = () => {
    const {boardnum} = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const cri = location.state;

    const [loginUser,setLoginUser] = useState(null);
    const [board,setBoard] = useState({boardtitle:"",boardcontents:"",userid:""});
    const [inputs,setInputs] = useState({boardtitle:board.boardtitle,boardcontents:board.boardcontents})

    // 원래 존재했던 파일들을 담은 배열
    const [orgFiles, setOrgFiles] = useState([]);
    // 렌더링을 위한 배열
    const [files, setFiles] = useState([]);
    
    //파일 태그의 고유 key로 사용될 INDEX
    const INDEX = useRef(0);
    //파일 태그의 렌더링시 표현될 번호를 위한 NUM
    const NUM = useRef(0);

    // 삭제되어야 할 파일 정보들
    const deleteFiles = useRef([]);
    //업로드 될 파일 데이터들을 담은 Ref
    const uploadFiles = useRef([]);
    //파일 태그들의 Ref
    const fileTags = useRef([]);

    const upload = (id) => {
        console.log("upload",id)
        fileTags.current[id].click();
    };

    const changeFile = (e,id,num) => {
        //변화가 일어난 file 태그 찾기
        const fileTag = fileTags.current[id];
        //업로드 된 파일 객체
        const file = fileTag.files[0];

        //만약 파일이 없다면
        if(!file){
            //새로 올렸던 파일이라면 삭제 진행
            if(id >= orgFiles.length){
                removeFile(id,num);
            }
            //기존 파일을 수정했다가 취소하는 것이라면 원래대로 돌아가기
            else{
                uploadFiles.current[id] = null;
                deleteFiles.current[id] = null;
                const newFiles = files.map(f => {
                    if (f.id === id) {
                        const fdto = orgFiles[id];
                        let ext = fdto.systemname.split(".").pop();
                        let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
                        return {
                            ...f,
                            file: {
                                name:fdto.orgname,
                                systemname:fdto.systemname
                            },
                            thumbnail:isThumbnail?`/api/file/thumbnail/${fdto.systemname}`:""
                        };
                    }
                    return f;
                });
                setFiles(newFiles);
            }
        }
        //파일이 있다면
        else{
            //확장자 확인
            let ext = file.name.split(".").pop();
            //썸네일이 필요한지 확인
            let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
            // 리더기 준비
            const reader = new FileReader();
            //실제로 업로드 될 파일 데이터들이 모이는 곳에다 파일 데이터 담기
            uploadFiles.current[id] = file;
            //id가 orgFiles.length보다 작다면 이는 기존 파일을 수정한 경우이다.
            if(id<orgFiles.length){
                deleteFiles.current[id] = orgFiles[id].systemname;
            }
            
            reader.onload = function(e){
                // 렌더링을 위한 files 변화시키기
                const newFiles = files.map(f => {
                    if (f.id === id) {
                        return { ...f, file: file, thumbnail:isThumbnail?e.target.result:''};
                    }
                    return f;
                });
                // 만약 마지막 파일 선택 버튼을 눌렀다면 마지막 파일 태그에 업로드를 했으므로 새로운 빈 파일태그를 생성하기 위해 files 변화
                if (num === NUM.current) {
                    newFiles.push({ id: ++INDEX.current, num:++NUM.current, file: '', thumbnail:''});
                }
                setFiles(newFiles);
            }
            reader.readAsDataURL(file)
        }

    }
    const removeFile = (id,num) => {
        console.log(num,NUM.current);
        if(num == NUM.current){
            //가장 마지막 첨부 삭제 버튼을 클릭한 경우
        }
        else{
            //그 외의 첨부 삭제 버튼을 클릭한 경우
            //렌더링을 위한 files 에서 객체들을 하나씩 꺼내오기
            //f : {id:1, num:1, file:파일객체 or fdto, thumbnail:??}
            files.map((f)=>{
                //삭제하고자 하는 파일의 id와 같은 객체를 찾은 경우
                if(f.id == id){
                    //file 프로퍼티의 size 확인(존재한다면 file객체, 없다면 가상의 fdto객체)
                    if(f.file.size){
                        //실제로 업로드 하려 했던 파일을 모아둔 곳에서도 삭제
                        uploadFiles.current.splice(id,1);
                    }
                    //원래 업로드 되어있던 파일의 첨부 삭제 버튼을 클릭한 경우이므로
                    else{
                        orgFiles[id] = null;
                        deleteFiles.current[id] = f.file.systemname;
                    }
                }
            })
            //렌더링도 변화를 주기
            //지워지는 f만 제외하고 나머지만 꺼내와서
            const updatedFiles = files.filter(f => f.id !== id)
            //반복문 돌며 num만 파일의 번호에 맞게 바꾸기
            .map((f, idx) => ({ ...f, num: idx }));
            //파일이 하나 줄었으므로 key에 해당하는 INDEX는 그대로지만, 눈에 보이는 NUM은 함께 줄어야 함
            NUM.current--;
            setFiles(updatedFiles);
        }
    }

    const change = (e)=>{
        const {name,value} = e.target;
        setInputs({...inputs,[name]:value})
    }

    const modify = () => {
        console.log("modify",uploadFiles.current)
        console.log("modify",inputs)
        console.log("modify",loginUser)
        const formData = new FormData();
        uploadFiles.current.map(file=>{
            if(file){
                formData.append("files",file);
            }
        })
        deleteFiles.current.map(systemname=>{
            formData.append("deleteFiles",systemname);
        })

        formData.append("boardtitle",inputs.boardtitle)
        formData.append("boardcontents",inputs.boardcontents);
        formData.append("userid",loginUser);

        axios.put(`/api/board/${boardnum}`,formData)
        .then(resp => {
            alert(`${resp.data}번 게시글 수정 성공!`)
            navigate(`/board/${boardnum}`,{state:cri});
        })
    }
    useEffect(() => {
        axios.get(`/api/user/loginCheck`)
            .then(resp => { setLoginUser(resp.data); })
            .catch(e => { setLoginUser(""); });
        axios.get(`/api/board/${boardnum}`)
            .then(resp => {
                setInputs(resp.data.board);
                setOrgFiles(resp.data.files);
                setBoard(resp.data.board);

                const temp = [];
                let i=0;
                for(;i<resp.data.files.length;i++){
                    const fdto = resp.data.files[i]
                    let ext = fdto.systemname.split(".").pop();
                    let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
                    temp.push(
                        {
                            id:i,
                            num:i,
                            file:{
                                name:fdto.orgname,
                                systemname:fdto.systemname
                            },
                            thumbnail:isThumbnail?`/api/file/thumbnail/${fdto.systemname}`:""
                        }
                    );
                }
                temp.push({id:i,num:i,file:"",thumbnail:""});
                setFiles(temp);
                INDEX.current = resp.data.files.length;
                NUM.current = INDEX.current;
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
                                <input type="text" name="boardtitle" maxLength={50} placeholder="제목을 입력하세요" defaultValue={board.boardtitle} onChange={change}/>
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
                                <textarea name="boardcontents" defaultValue={board.boardcontents} onChange={change}></textarea>
                            </div>
                        </div>
                        {
                            files.map(file => {
                                return (
                                    <div className={`row r${file.num}`} key={file.id}>
                                        <div>파일 첨부{file.num + 1}</div>
                                        <div className={`file${file.num}_cont row`}>
                                            <div className="cols-7">
                                                <input
                                                    type="file"
                                                    name="files"
                                                    id={`file${file.num}`}
                                                    style={{ display: 'none' }}
                                                    onChange={(e) => changeFile(e, file.id, file.num)}
                                                    ref={el=>fileTags.current[file.id] = el}
                                                />
                                                <span id={`file${file.num}name`}>{file.file.name || '선택된 파일 없음'}</span>
                                            </div>
                                            <div className="row cols-3">
                                                <Button className="btn" value="파일 선택" onClick={() => upload(file.id)}></Button>
                                                <Button className="btn" value="첨부 삭제" onClick={() => removeFile(file.id,file.num)}></Button>
                                            </div>
                                            {file.thumbnail?
                                            <div className="thumbnail_area">
                                                {file.thumbnail && <img src={file.thumbnail} alt={`thumbnail${file.NUM}`} className="thumbnail" />}
                                            </div>
                                            :""
                                            }
                                        </div>
                                    </div>
                                )
                            })
                        }
                        <input type="hidden" name="updateCnt" id="updateCnt"/>
                    </div>
                </form>
                <table className="btn_area">
                    <tbody>
                        <tr>
                            <td>
                                <Button value="수정완료" className="btn" onClick={modify}></Button>
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
export default Modify;