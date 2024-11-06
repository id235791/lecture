import { useLocation, useNavigate } from "react-router-dom";
import Header from "../../layout/Header";
import Button from "../../components/Button";
import { useEffect, useRef, useState } from "react";
import axios from "axios";

const Write = () => {
    const location = useLocation();
    const cri = location.state;

    const navigate = useNavigate();

    const [inputs, setInputs] = useState({boardtitle:"", boardcontents:""})
    const change = (e)=>{
        const {name,value} = e.target;
        setInputs({...inputs,[name]:value});
    }

    const index = useRef(0);    
    const [files, setFiles] = useState([{id:0,file:"",thumbnail:""}]);
    const upload = (id) => {
        document.getElementById("file"+id).click();
    }
    const selectFile = (e,id) => {
        const file = e.target.files[0];
        if(!file){
            //파일첨부 칸 삭제
            removeFile(id);
        }
        else{
            let ext = file.name.split(".").pop();
            let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
            const reader = new FileReader();
            reader.onload = (e) => {
                const newFiles = files.map(item => {
                    if(item.id == id){
                        return { ...item, file:file?file:'', thumbnail:isThumbnail?e.target.result:'' };
                    }
                    return item;
                })
                if(id == index.current){
                    index.current++;
                    newFiles.push({id:index.current, file:'', thumbnail:''});
                }
                setFiles(newFiles);
            }
            reader.readAsDataURL(file);
        }
    }
    
    const removeFile = (id) => {
        if(id != index.current){
            //[{0},{1},{2},{3}]
            //[{0},{1},{3}]
            const updatedFiles = files.filter( item => item.id != id ).map((item,idx)=>{
                return {...item, id:idx};
            })
            index.current--;
            setFiles(updatedFiles);
        }
    }

    const regist = () => {
        const formData = new FormData();
        files.map((item) => {
            if(item.file){
                formData.append("files",item.file);
            }
        })
        formData.append("boardtitle",inputs.boardtitle);
        formData.append("boardcontents",inputs.boardcontents);

        axios.post(`/api/board/write`,formData)
        .then(resp=>{
            let boardnum = resp.data;
            alert(`${boardnum}번 게시글 등록 성공!`);
            navigate('/board/list',{state:cri});
        })
        .catch(err => {
            console.log(err);
            alert('게시글 등록 실패!');
        })
    }

    useEffect(()=>{
        axios.get(`/api/user/loginCheck`).then(resp=>{
            if(resp.data.trim() != ""){
                document.boardForm.userid.value = resp.data.trim();
            }
        })
    })

    return (
        <div id="wrap" className="write">
            <Header></Header>
            <form id="boardForm" name="boardForm">
                <div className="table">
                    <div className="row">
                        <div>제목</div>
                        <div>
                            <input type="text" name="boardtitle" maxLength={50} placeholder="제목을 입력하세요" onChange={change}/>
                        </div>
                    </div>
                    <div className="row">
                        <div>작성자</div>
                        <div>
                            <input type="text" name="userid" maxLength={50} readOnly />
                        </div>
                    </div>
                    <div className="row">
                        <div>내용</div>
                        <div>
                            <textarea name="boardcontents" onChange={change}></textarea>
                        </div>
                    </div>
                    {
                        files.map((item) => {
                            return (
                                <div className={`row r${item.id}`} key={item.id}>
                                    <div>파일 첨부{item.id + 1}</div>
                                    <div className={`file${item.id}_cont row`}>
                                        <div className="cols-7">
                                            <input type="file" name="files" id={`file${item.id}`} style={{display:'none'}} onChange={(e)=>{
                                                selectFile(e,item.id);
                                            }}></input>
                                            <span id={`file${item.id}name`}>{item.file.name || '선택된 파일 없음'}</span>
                                        </div>
                                        <div className="cols-3 row">
                                            <Button className={"btn"} value={"파일 선택"} onClick={()=>{
                                                upload(item.id);
                                            }}></Button>
                                            <Button className={"btn"} value={"첨부 삭제"} onClick={()=>{
                                                removeFile(item.id);
                                            }}></Button>
                                        </div>
                                        {
                                            item.thumbnail ? 
                                            <div className="thumbnail_area">
                                                <img src={item.thumbnail} alt={`thumbnail${item.id}`} className="thumbnail"></img>
                                            </div>
                                            :
                                            ""
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
                            <Button value="등록" className="btn" onClick={regist}></Button>
                            <Button value="목록" className="btn" onClick={()=>{
                                navigate("/board/list",{state:cri});
                            }}></Button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}
export default Write;