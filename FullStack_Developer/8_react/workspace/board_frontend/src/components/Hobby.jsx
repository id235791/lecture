import { useEffect, useState } from "react";
import Button from "./Button";

const Hobby = ({list}) => {
    const [hobbyList,setHobbyList] = useState([]);
    const [userhobby,setUserhobby] = useState("");
    const addHobby = (e) => {
        const joinForm = document.joinForm;
        let hobby = joinForm.hobby;
        if(hobby.value == ""){
            alert("취미를 입력해 주세요.")
            hobby.focus();
            return;
        }
        if(hobbyList.indexOf(hobby.value) != -1){
            alert("중복된 취미입니다.")
            hobby.focus();
            hobby.value = "";
            return;
        }
        if(!/^[가-힣a-zA-Z\s]+$/.test(hobby.value)){
            alert("정확한 취미를 입력해 주세요.")
            hobby.focus();
            hobby.value = "";
            return;
        }
        if(hobbyList.length >= 5){
            alert("취미는 5개 이하로 입력해 주세요.")
            hobby.focus();
            hobby.value="";
            return;
        }
        setHobbyList([...hobbyList,hobby.value])
        hobby.value="";
        hobby.focus();
    }

    const hobbyKeyUp = (e) => {
        if(e.key === "Enter"){
            addHobby();
        }
    }

    const deleteHobby = (hobby)=>{
        const updated = [];
        for(const h of hobbyList){
            if(hobby != h){
                updated.push(h);
            }
        }
        setHobbyList(updated);
    }

    useEffect(()=>{
        if(list){
            setHobbyList(list);
        }
    },[])
    useEffect(()=>{
        setUserhobby(hobbyList.join("\\"));
    },[hobbyList])

    return(
        <div>
            <div className="hobby_input">
                <input type="text" name="hobby" onKeyUp={hobbyKeyUp}/><Button value="추가" onClick={addHobby}></Button>
            </div>
            <div className="hobby_list">
            {
                hobbyList.map((h)=>{
                    return(
                        <span className="userhobby" name="userhobby" onClick={()=>{deleteHobby(h)}}>
                            <span>{h}</span>
                            <a className="xBox" onClick={()=>{
                                deleteHobby(h);
                            }}></a>
                        </span>
                    )
                })
            }
            </div>
            <input type="hidden" value={userhobby} name="userhobby" readOnly/>
        </div>
    )

}
export default Hobby;