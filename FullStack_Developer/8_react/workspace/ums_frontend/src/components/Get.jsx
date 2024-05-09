import Button from "./Button";

const Get = ({data,clickList,clickLike,loginUser,clickModify,clickRemove}) => {

    return (<>
        <div id="wrap" className="get">
            <div id="get">
                <div className="row">
                    <div className="prodname">상품명</div>
                    <div className="get_area">
                        {data.prodname}
                    </div>
                </div>
                <div className="row">
                    <div className="userid">판매자</div>
                    <div className="get_area">
                        {data.userid}
                    </div>
                </div>
                <div className="row">
                    <div className="prodprice">판매 가격</div>
                    <div className="get_area">
                        {data.prodprice}
                    </div>
                </div>
                <div className="row">
                    <div className="prodprice">판매 수량</div>
                    <div className="get_area">
                        {data.prodamount}
                    </div>
                </div>
                <div className="row">
                    <div className="prodprice">카테고리</div>
                    <div className="get_area">
                        {data.prodcategory}<span className="like" onClick={clickLike}>♡</span> <span>{data.likecnt}</span>
                    </div>
                </div>
                <div className="row">
                    <div className="prodinfo">상품설명</div>
                    <div className="get_area">
                        <textarea name="prodinfo" defaultValue={data.prodinfo} readOnly></textarea>
                    </div>
                </div>
                <div className="btn_area center">
                    {
                        loginUser == data.userid?
                        <>
                            <Button text="수정" onClick={clickModify}></Button>
                            <Button text="삭제" onClick={clickRemove}></Button>
                        </>:
                        <></>
                    }
                    <Button text="목록으로" size="common" onClick={clickList}/>
                </div>
            </div>
        </div>
    </>)
}
export default Get;