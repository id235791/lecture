import { useRef } from "react";
import { useState } from "react";

function useBoardList(){
    const [list,setList] = useState([]);
    const INDEX = useRef(0);
    const NUM = useRef(0);
    
    const remove = (key) => {
        for(let i=0;i<list.length;i++){
            if(list[i].key == key){
                list[i] = null;
            }
            else if(list[i].key > key){
                list[i].num -= 1;
            }
        }
        NUM.current -= 1;
        setList(list.filter((item) => item != null));
    }
    const write = () => {
        const newEl = {key:INDEX.current,num:NUM.current};
        INDEX.current += 1;
        NUM.current += 1;
        setList((prevList)=>
            [newEl,...prevList]
        )
    }
    return {"list":list, "remove":remove, "write":write};
}

export default useBoardList;