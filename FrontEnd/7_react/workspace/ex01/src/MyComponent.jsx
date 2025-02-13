import { useState } from "react";

const MyComponent = (props) => {
    const [data,setData] = useState(1);

    const handleClickBtn = () => {
        setData(data+1);
    }

    return (
        <>
            <h2>이것은 {props.name}의 컴포넌트</h2>
            <p>여러개의 요소가 있을 시 부모태그 하나로 감싸주어야 함</p>
            <p>리렌더링 : {data}</p>
            <p><input type="button" value="버튼" onClick={handleClickBtn}></input></p>
        </>
    )
}
export default MyComponent;