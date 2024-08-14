import { useEffect, useRef, useState } from "react";

const Dropdown = ({list, name, width, onChange, value}) => {
    let label;
    for(const key in list){
        if(list[key] == value){
            label = key;
            break;
        }
    }
    const [isOpen, setIsOpen] = useState(false);
    const [selectedValue,setSelectedValue] = useState(value);
    const [selectedLabel,setSelectedLabel] = useState("");
    const dropdownRef = useRef(null);

    

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    }
    const clickElement = (e) => {
        let value;
        let label = e.target.innerHTML;
        value = list[label];
        setSelectedValue(value);
        setSelectedLabel(label);
        setIsOpen(false);
        document.getElementById(name).value = value;
        if(onChange){
            onChange(document.getElementById(name).value);
        }
    }
    const el = [];
    for(const key in list){
        el.push(<li key={key} onClick={clickElement} style={{width:`${width}px`}}>{key}</li>)
    }
    useEffect(() => {
        const handleClickOutside = (e) => {
            if(dropdownRef.current && !dropdownRef.current.contains(e.target)){
                setIsOpen(false);
            }
        }
        document.addEventListener('click',handleClickOutside);
        return () => {
            document.removeEventListener('click',handleClickOutside);
        }
    },[])
    useEffect(()=>{
        const label = Object.keys(list).find(key => list[key] === value);
        if (label) {
            setSelectedLabel(label);
            setSelectedValue(value);
        }
    }, [value, list]);
    return (

        <div ref={dropdownRef} className={`common-dropdown ${isOpen?'show':''}`} style={{width:`${width}px`}}>
            <input type="hidden" defaultValue={selectedValue} name={name} id={name}></input>
            <input type="button" className="dropdown-button" defaultValue={selectedLabel} style={{width:`${width}px`}} onClick={toggleDropdown}></input>
            <div className={`dropdown-content ${isOpen?'show':''}`}>
                <ul style={{width:`${width}px`}}>
                    {
                        el
                    }
                </ul>
            </div>
        </div>
    )
}
export default Dropdown;