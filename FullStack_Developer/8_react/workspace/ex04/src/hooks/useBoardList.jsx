import { useEffect, useRef, useState } from "react";

const useBoardList = (path) => {
    const [list, setList] = useState([]);
    const KEY = useRef(0);
    const NUM = useRef(0);

    useEffect(()=>{
        //백 통신
        console.log(path);
    },[])

    const remove = (key)=>{
        for(let i=0;i<list.length;i++){
            if(list[i].key == key){
                list[i] = null;
            }
            else if(list[i].key > key){
                list[i].key -= 1;
            }
        }
        KEY.current -= 1;
        setList(
            list.filter(
                (item) => item!=null
            )
        );
    }
    const write = ()=>{
        const board = {type:path,key:KEY.current,num:NUM.current};
        KEY.current += 1;
        NUM.current += 1;

        setList(
            (prevList)=>[board,...prevList]
        );
    }

    return {"list":list, "remove":remove, "write":write};
}
export default useBoardList;