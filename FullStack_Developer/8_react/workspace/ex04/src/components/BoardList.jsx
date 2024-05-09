const BoardList = ({list,remove,write}) => {
    return (
        <>
            <ul>
                {
                    list.map(
                        (board)=>{
                            const {num,key} = board;
                            const clickRemove = (e)=>{
                                e.preventDefault();
                                remove(key);
                            }
                            return (
                                <li className={`r${num}`}>
                                    게시글 번호 {num+1} ({key}번째 요소)
                                    <a href="#" onClick={clickRemove}>삭제</a>
                                </li>
                            )
                        }
                    )
                }
            </ul>
            <input type="button" value="게시글추가" onClick={write}></input>
        </>
    )
}
export default BoardList;