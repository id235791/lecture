import Dropdown from "./Dropdown";
import Button from "./Button";

const Add = ({data,clickAdd,clickList}) => {
    const categories = ["전체","패션의류/잡화","뷰티","출산/유아동","식품","주방용품","생활용품","홈인테리어","가전디지털","스포츠/레저","자동차용품","도서/음반/DVD","완구/취미","문구/오피스","반려동물용품","헬스/건강식품"];

    return (<>
        <div id="wrap" className="add">
            <form action="/prod/add" method="post" name="addForm">
                <div id="add">
                    <div className="row">
                        <div className="prodname">상품명</div>
                        <div className="input_area">
                            <input type="text" name="prodname" maxLength={20} defaultValue={""}/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="userid">판매자</div>
                        <div className="input_area">
                            <input type="text" name="userid" defaultValue={data.loginUser} readOnly/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="prodprice">판매 가격</div>
                        <div className="input_area">
                            <input type="text" name="prodprice" defaultValue={""}/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="prodprice">판매 수량</div>
                        <div className="input_area">
                            <input type="text" name="prodamount" defaultValue={""}/>
                        </div>
                    </div>
                    <div className="row category">
                        <div className="prodprice">카테고리</div>
                        <Dropdown list={categories} name="prodcategory" width={400}/>
                    </div>
                    <div className="row">
                        <div className="prodinfo">상품설명</div>
                        <div className="input_area">
                            <textarea name="prodinfo" defaultValue={""}></textarea>
                        </div>
                    </div>
                    <div className="btn_area center">
                        <Button text="등록하기" size="common" onClick={clickAdd}/>
                        <Button text="목록으로" size="common" onClick={clickList}/>
                    </div>
                </div>
            </form>

        </div>
    </>)
}
export default Add;