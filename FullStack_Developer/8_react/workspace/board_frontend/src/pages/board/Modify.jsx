import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Button from "../../components/Button";
import Header from "../../layout/Header";

const Modify = () => {
    const location = useLocation();
    const {cri,boardnum} = location.state // { "cri" : {} , "boardnum" : 133}
    const navigate = useNavigate();
    
    const [inputs,setInputs] = useState();
    //렌더링을 위한 배열 > 실제 화면에 표현될 아이템들이 담겨있는 배열
    const [files, setFiles] = useState();

    const [board, setBoard] = useState(null);
    //원래 존재했던 FileDTO들을 담을 배열 > 원상복귀가 필요할 때 사용하기 위해서
    const [orgFiles, setOrgFiles] = useState();

    //파일의 고유 Key(id)로 사용될 INDEX
    const index = useRef(0);
    //파일 태그의 렌더링시 표현될 번호를 위한 NUM
    const NUM = useRef(0);
    //삭제되어야 할 파일 정보들을 담을 배열
    const deleteFiles = useRef([]);
    //업로드 될 파일 데이터들을 담을 배열
    const uploadFiles = useRef([]);

    const upload = (id) => {
        console.log(id);
        document.getElementById("file"+id).click();
    }

    const selectFile = (e,id,num) => {
        //변화가 일어난 파일 태그
        const fileTag = e.target;
        //업로드 된 실제 파일 객체(데이터)
        const file = fileTag.files[0];

        //만약 파일이 없다면
        if(!file){
            //새로 올렸었던 파일이라면 삭제 진행
            if(id >= orgFiles.length){
                removeFile(id,num);
            }
            //기존 파일을 수정했다가 취소하는 것이라면 원래대로 돌아가기
            else{
                //uploadFiles[id] 에는 기존 파일을 대체할 파일 데이터가 담겨있을 것
                //deleteFiles[id] 에는 그 대체된 기존 파일의 이름이 담겨있을 것
                //수정했던 것을 취소하는 것이기 때문에 담겨있던 것들을 다 null로 바꿔주기 
                uploadFiles.current[id] = null;
                deleteFiles.current[id] = null;

                const newFiles = files.map((item)=>{
                    //렌더링 되고 있는 아이템들을 하나씩 꺼내오며 id가 같을 때(원래대로 돌아가야 하는 아이템)를 찾음
                    if(item.id == id){
                        //orgFiles에 보관되어 있던 원래 FileDTO를 가지고 와서
                        const fdto = orgFiles[id];
                        let ext = fdto.systemname.split(".").pop();
                        let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp'; 
                        return {
                            //나머지들(num, key)은 그대로
                            ...item,
                            //눈에 보여질 파일 명은 기존것으로 바뀌어야 하므로 fdto로 원상복귀
                            file:{
                                name:fdto.orgname,
                                systemname:fdto.systemname
                            },
                            thumbnail:isThumbnail?`/api/file/thumbnail/${fdto.systemname}`:""
                        }
                    }
                    //그 외에는 상관 없는 item들 이므로 그대로 다시 return
                    return item;
                });
                //여기까지 왔으면 원상복귀 된 모습의 새로운 배열이 완성됐다.
                //렌더링 변화를 위해 Files State 변화
                setFiles(newFiles);
            }
        }
        else{
            let ext = file.name.split(".").pop();
            let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
            //실제로 업로드 될 파일 데이터들이 모이는 곳에다 파일 데이터 담기
            uploadFiles.current[id] = file;
            //id가 orgFiles.length보다 작다면 이는 기존 파일을 수정한 경우이다.
            if(id < orgFiles.length){
                //기존 파일(삭제될 파일)의 이름을 deleteFiles에 담기
                deleteFiles.current[id] = orgFiles[id].systemname;
            }
            const reader = new FileReader();
            reader.onload = (e) => {
                const newFiles = files.map(item => {
                    if(item.id == id){
                        return { ...item, file:file?file:'', thumbnail:isThumbnail?e.target.result:'' };
                    }
                    return item;
                })
                //마지막 파일선택 버튼을 클릭한 경우
                if(num == NUM.current){
                    index.current++;
                    NUM.current++;
                    newFiles.push({id:index.current, num:NUM.current, file:'', thumbnail:''});
                }
                setFiles(newFiles);
            }
            reader.readAsDataURL(file);
        }
    }

    const removeFile = (id,num) => {
        if(num == NUM.current){
            //가장 마지막 첨부 삭제 버튼을 클릭한 경우
        }
        else{
            //그 외의 첨부 삭제 버튼을 클릭한 경우
            files.map((item) => {
                //렌더링 되고 있는 item중에 삭제될 item을 찾고
                if(item.id == id){
                    //그 item이 가지고 있는 file을 확인
                    //1. FileDTO로 만들어진 임의의 객체 - .size 프로퍼티가 없음
                    //2. 실제 파일 객체(데이터) - .size 프로퍼티가 존재 함
                    if(item.file.size){
                        //실제 파일이 지워져야 하므로 업로드 되려던 예비 파일(uploadFiles) 중
                        //해당하는 것을 찾아 지우기
                        uploadFiles.current.splice(id,1);
                    }
                    else{
                        //원래 업로드 되어있었던 파일 중 하나를 지우겠다고 한 경우
                        //지워져야 할 파일들(deleteFiles)에 해당하는 파일의 systemname 추가
                        deleteFiles.current[id] = item.file.systemname;
                    }
                }
            })
            //렌더링도 변화를 주기
            const updatedFiles = files.filter(item => item.id != id).map((item,idx)=>{
                return {...item,num:idx};
            })
            //key에 해당하는 INDEX는 그대로지만, 눈에 보일 숫자인 NUM은 하나 줄어야 함
            NUM.current--;
            setFiles(updatedFiles);
        }
    }

    const change = (e) => {
        const {name,value} = e.target;
        setInputs({...inputs,[name]:value});
    }

    const modify = () => {
        console.log("modify",uploadFiles.current);
        console.log("modify",deleteFiles.current);
        console.log("modify",inputs);

        const formData = new FormData()
        uploadFiles.current.map(file => {
            if(file){
                formData.append("files",file);
            }
        })
        deleteFiles.current.map(systemname => {
            if(systemname){
                formData.append("deleteFiles",systemname);
            }
        })
        formData.append("boardtitle",inputs.boardtitle);
        formData.append("boardcontents",inputs.boardcontents);

        axios.put(`/api/board/${boardnum}`,formData)
        .then((resp) => {
            alert(`${resp.data}번 게시글 수정 완료!`);
            navigate(`/board/${boardnum}`,{state:cri});
        })
    }

    useEffect(()=>{
        axios.get(`/api/board/${boardnum}`)
        .then((resp)=>{
            setOrgFiles(resp.data.files);
            setInputs(resp.data.board);
            setBoard(resp.data.board);

            const temp = [];
            let i=0;
            for(;i<resp.data.files.length;i++){
                const fdto = resp.data.files[i];
                let ext = fdto.systemname.split(".").pop();
                let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
                temp.push({
                    id:i,
                    num:i,
                    file:{
                        name:fdto.orgname,
                        systemname:fdto.systemname
                    },
                    thumbnail:isThumbnail?`/api/file/thumbnail/${fdto.systemname}`:''
                })
            }
            temp.push({id:i,num:i,file:"",thumbnail:""});
            setFiles(temp);
            index.current = resp.data.files.length;
            NUM.current = index.current;
            console.log(index.current, NUM.current, temp)
        })
    },[])
    if(!board){
        return <>로딩중...</>
    }
    else{
        return (
            <div id="wrap" className="get">
                <Header></Header>
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
                            files.map(item => {
                                return (
                                    <div className={`row r${item.num}`} key={item.id}>
                                        <div>파일 첨부{item.num + 1}</div>
                                        <div className={`file${item.num}_cont row`}>
                                            <div className="cols-7">
                                                <input
                                                    type="file"
                                                    name="files"
                                                    id={`file${item.num}`}
                                                    style={{ display: 'none' }}
                                                    onChange={(e)=>{
                                                        selectFile(e,item.id,item.num);
                                                    }}
                                                />
                                                <span id={`file${item.num}name`}>{item.file.name || '선택된 파일 없음'}</span>
                                            </div>
                                            <div className="row cols-3">
                                                <Button className="btn" value="파일 선택" onClick={()=>{ upload(item.num) }}></Button>
                                                <Button className="btn" value="첨부 삭제" onClick={()=>{ removeFile(item.id, item.num) }}></Button>
                                            </div>
                                            {item.thumbnail?
                                            <div className="thumbnail_area">
                                                <img src={item.thumbnail} alt={`thumbnail${item.id}`} className="thumbnail" />
                                            </div>
                                            :""
                                            }
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