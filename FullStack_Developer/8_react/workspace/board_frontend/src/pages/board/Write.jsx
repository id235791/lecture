import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Header from "../../layout/Header";
import Button from "../../components/Button";

const Write = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const cri = location.state;
    const [loginUser, setLoginUser] = useState(null);
    const index = useRef(1);
    const [files, setFiles] = useState([{ id: 0, file: "", thumbnail:"" }]);
    const [inputs,setInputs] = useState({boardtitle:"",boardcontents:""})

    useEffect(() => {
        axios.get(`/api/user/loginCheck`)
            .then(resp => { setLoginUser(resp.data); })
            .catch(e => { setLoginUser(""); });
    }, []);

    const change = (e)=>{
        const {name,value} = e.target;
        setInputs({...inputs,[name]:value})
    }

    const selectFile = (e, id) => {
        const file = e.target.files[0];
        if (!file) {
            removeFile(id);
        } else {
            let ext = file.name.split(".").pop();
            let isThumbnail = ext == 'jpeg' || ext == 'jpg' || ext == 'png' || ext == 'gif' || ext == 'webp';
            const reader = new FileReader();
            reader.onload = function(e){
                const newFiles = files.map(f => {
                    if (f.id === id) {
                        return { ...f, file: file ? file : '', thumbnail:isThumbnail?e.target.result:''};
                    }
                    return f;
                });
                if (id === index.current - 1) {
                    newFiles.push({ id: index.current++, file: '', thumbnail:''});
                }
                setFiles(newFiles);
            }
            reader.readAsDataURL(file)
        }
    };

    const removeFile = (id) => {
        if(id !== index.current - 1){
            const updatedFiles = files.filter(
                f => f.id !== id
            ).map((f, idx) => ({ ...f, id: idx }));
            index.current = updatedFiles.length;
            setFiles(updatedFiles);
        }
    };

    const upload = (id) => {
        document.querySelector(`#file${id}`).click();
    };

    const regist = () => {
        const formData = new FormData();
        const arFiles = [];
        files.map(file=>{
            if(file.file){
                // arFiles.push(file.file);
                formData.append("files",file.file)
            }
        })
        // console.log(arFiles);
        // formData.append("files",arFiles);
        formData.append("boardtitle",inputs.boardtitle)
        formData.append("boardcontents",inputs.boardcontents);
        formData.append("userid",loginUser);

        axios.post("/api/board/regist",formData)
        .then(resp => {
            let boardnum = resp.data;
            alert(`${boardnum}번 게시글 등록 성공!`);
            navigate("/board/list", { state: cri });
        });
    };

    if (loginUser === null) {
        return <>로딩중...</>;
    } else {
        console.log(files);
        return (
            <div id="wrap" className="write">
                <Header loginUser={loginUser}></Header>
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
                                <input type="text" name="userid" maxLength={50} value={loginUser} readOnly />
                            </div>
                        </div>
                        <div className="row">
                            <div>내용</div>
                            <div>
                                <textarea name="boardcontents" onChange={change}></textarea>
                            </div>
                        </div>
                        {
                            files.map(file => {
                                return (
                                    <div className={`row r${file.id}`} key={file.id}>
                                        <div>파일 첨부{file.id + 1}</div>
                                        <div className={`file${file.id}_cont row`}>
                                            <div className="cols-7">
                                                <input
                                                    type="file"
                                                    name="files"
                                                    id={`file${file.id}`}
                                                    style={{ display: 'none' }}
                                                    onChange={(e) => selectFile(e, file.id)}
                                                />
                                                <span id={`file${file.id}name`}>{file.file.name || '선택된 파일 없음'}</span>
                                            </div>
                                            <div className="row cols-3">
                                                <Button className="btn" value="파일 선택" onClick={() => upload(file.id)}></Button>
                                                <Button className="btn" value="첨부 삭제" onClick={() => removeFile(file.id)}></Button>
                                            </div>
                                            {file.thumbnail?
                                            <div className="thumbnail_area">
                                                {file.thumbnail && <img src={file.thumbnail} alt={`thumbnail${file.id}`} className="thumbnail" />}
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
                                <Button value="등록" className="btn" onClick={regist}></Button>
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
};
export default Write;
