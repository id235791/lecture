import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";

const Product = () => {
    const [product,setProduct] = useState({});
    //useParams() : Path Variable을 추출하는 방법, 넘겨진 파라미터 명들을 Key로 가지는 객체
    // const params = useParams();
    const {prodnum} = useParams();
    useEffect(()=>{
        //useEffect(콜백함수) : 페이지 렌더링 후 가장먼저 넘겨진 콜백함수를 호출
        //useEffect(콜백함수,[]) : 최초 렌더링 시에만 호출
        //useEffect(콜백함수,[state1,state2,...]) : state1, state2, ... 값이 변할 시 호출
        //실제로는 이 아래에서 Ajax를 이용해 백엔드 서버와 통신 후 데이터를 받아옴
        const products = [
            {
                prodnum:1,
                prodname:"연필",
                prodprice:1000
            },
            {
                prodnum:2,
                prodname:"지우개",
                prodprice:2000
            },
            {
                prodnum:3,
                prodname:"볼펜",
                prodprice:3000
            },
        ]
        for(let item of products){
            // if(item.prodnum == params.prodnum){
            if(item.prodnum == prodnum){
                setProduct(item);
                break;
            }
        }
    },[])
    return (
        <>
            상품번호 : {product.prodnum}<br/>
            상품명 : {product.prodname}<br/>
            상품가격 : {product.prodprice}<br/>
        </>
    )
}
export default Product;