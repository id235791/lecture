import { useEffect, useRef, useState } from "react";

const Dropdown = ({list, name, width, value}) => {
    //list : {"전체" : "a", "제목 또는 작성자" : "TW"}
    //name : 백 서버로 전송하기 위한 key 이름
    //value : 초기에 드롭다운이 나타낼 값
    let label;
    for(const key in list){
        if(list[key] == value){
            label = key;
            break;
        }
    }

    const [isOpen, setIsOpen] = useState(false);
    const [selectedValue, setSelectedValue] = useState(value);
    const [selectedLabel, setSelectedLabel] = useState("");
    const dropdownRef = useRef(null);

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    }
    const clickElement = (e) => {
        let value;
        let label = e.target.innerHTML; //"제목 또는 작성자"
        value = list[label]; // list["제목 또는 작성자"] --> "TW"
        setSelectedLabel(label);
        setSelectedValue(value);
        setIsOpen(false);
        document.getElementById(name).value = value;
    }
    const elList = [];
    for(const key in list){
        elList.push(<li key={key} onClick={clickElement} style={{width:`${width}px`}}>{key}</li>)
    }

    useEffect(()=>{
        const handleClickOutside = (e) => {
            if(dropdownRef.current && !dropdownRef.current.contains(e.target)){
                setIsOpen(false);
            }
        }
        document.addEventListener('click',handleClickOutside);
        return ()=>{
            document.removeEventListener("click",handleClickOutside);
        }
    },[])
    useEffect(()=>{
        //Object.keys(객체) -> 해당 객체의 키들만 모여있는 배열
        //배열.find(key => 조건식) : 조건식이 참일때의 key를 리턴
        const label = Object.keys(list).find(key => list[key] == value);
        if(label){
            setSelectedLabel(label);
            setSelectedValue(value);
        }
    },[value,label])


    return (
        <div ref={dropdownRef} className={`common-dropdown ${isOpen?'show':''}`} style={{width:`${width}px`}}>
            <input type="hidden" defaultValue={selectedValue} name={name} id={name}/>
            <input type="button" className="dropdown-button" defaultValue={selectedLabel} style={{width:`${width}px`}} onClick={toggleDropdown}/>
            <div className={`dropdown-content ${isOpen?'show':''}`}>
                <ul style={{width:`${width}px`}}>
                    {elList}
                </ul>
            </div>
        </div>
    )
}
export default Dropdown;