import React, { useEffect, useRef, useState } from 'react';

const Dropdown = ({ list, name, width,onChange,value}) => {
  const [isOpen, setIsOpen] = useState(false);
  const selectedItem = useRef("");
  const dropdownRef = useRef(null);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };
  const clickElement = (e) => {
    const value = e.target.innerHTML;
    selectedItem.current = value;
    document.getElementById(name).value = value;
    if(value == "전체"){
      document.getElementById(name).value = 'none';
    }
    setIsOpen(false);
    if(onChange){
      onChange(document.getElementById(name).value);
    }
  }
  useEffect(() => {
    // 클릭 이벤트 핸들러
    const handleClickOutside = (event) => {
      // 만약 클릭된 요소가 드롭다운이 아니면 닫기
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    };

    // document에 클릭 이벤트 등록
    document.addEventListener('click', handleClickOutside);

    // 컴포넌트가 언마운트될 때 이벤트 핸들러 정리
    return () => {
      document.removeEventListener('click', handleClickOutside);
    };
  }, []); // 빈 배열은 컴포넌트가 마운트될 때만 실행

  return (
    <div ref={dropdownRef} className={`common-dropdown ${isOpen ? 'show' : ''}`} style={{width:`${width}px`}}>
      <input type="hidden" value={selectedItem.current} name={name} id={name}/>
      <input type="button" onClick={toggleDropdown} className="dropdown-button" value={selectedItem.current == ""?(value?value:"카테고리"):selectedItem.current} style={{width:`${width}px`}}/>
      <div className={`dropdown-content ${isOpen ? 'show' : ''}`}>
        <ul style={{width:`${width}px`}}>
          {/* <li key={"전체"} onClick={clickElement} style={{width:`${width}px`}}>전체</li> */}
          {list.map((item) => {
            return <li key={item} onClick={clickElement} style={{width:`${width}px`}}>{item}</li>;
          })}
        </ul>
      </div>
    </div>
  );
};

export default Dropdown;