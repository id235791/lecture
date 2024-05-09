import Dropdown from "./Dropdown";
import Button from "./Button";

const Modify = ({data,clickModify,clickList}) => {
    const categories = ["패션의류/잡화","뷰티","출산/유아동","식품","주방용품","생활용품","홈인테리어","가전디지털","스포츠/레저","자동차용품","도서/음반/DVD","완구/취미","문구/오피스","반려동물용품","헬스/건강식품"];
    const product = data.product;

    return (<>
        <div id="wrap" className="modify">
            <form action="/prod/modify" method="post" name="modifyForm">
                <div id="modify">
                    <div className="row">
                        <div className="prodname">상품명</div>
                        <div className="input_area">
                            <input type="text" name="prodname" maxLength={20} defaultValue={product.prodname}/>
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
                            <input type="text" name="prodprice" defaultValue={product.prodprice}/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="prodprice">판매 수량</div>
                        <div className="input_area">
                            <input type="text" name="prodamount" defaultValue={product.prodamount}/>
                        </div>
                    </div>
                    <div className="row category">
                        <div className="prodprice">카테고리</div>
                        <Dropdown list={categories} name="prodcategory" width={400} value={product.prodcategory}/>
                    </div>
                    <div className="row">
                        <div className="prodinfo">상품설명</div>
                        <div className="input_area">
                            <textarea name="prodinfo" defaultValue={product.prodinfo}></textarea>
                        </div>
                    </div>
                    <div className="btn_area center">
                        <Button text="수정완료" size="common" onClick={clickModify}/>
                        <Button text="목록으로" size="common" onClick={clickList}/>
                    </div>
                </div>
            </form>

        </div>
    </>)
}
export default Modify;