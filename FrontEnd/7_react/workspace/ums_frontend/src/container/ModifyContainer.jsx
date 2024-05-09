import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from 'axios';
import Modify from "../components/Modify";
import Header from "../components/layout/Header";
import Footer from "../components/layout/Footer";

const ModifyContainer = () => {
    const [data,setData] = useState();
    const {prodnum} = useParams();

    const clickModify = (e) => {
        e.preventDefault();
        const modifyForm = document.modifyForm;
        
        const prodname = modifyForm.prodname;
        if(prodname.value == ""){
            alert("상품명을 입력해주세요!");
            prodname.focus();
            return;
        }

        const prodprice = modifyForm.prodprice;
        if(prodprice.value == ""){
            alert("상품가격을 입력해주세요!")
            prodprice.focus();
            return;
        }

        const prodamount = modifyForm.prodamount;
        if(prodamount.value == ""){
            alert("상품수량을 입력해주세요!")
            prodamount.focus();
            return;
        }

        const prodcategory = modifyForm.prodcategory;

        const prodinfo = modifyForm.prodinfo;
        if(prodinfo.value == ""){
            alert("상품설명을 입력해주세요!");
            prodinfo.focus();
            return;
        }
        if(prodinfo.value.length < 10){
            alert("상품설명은 10자 이상으로 입력해주세요!");
            prodinfo.focus();
            return;
        }

        const userid = modifyForm.userid;

        const product = {
            prodnum:prodnum,
            prodname:prodname.value,
            prodprice:prodprice.value,
            prodamount:prodamount.value,
            prodcategory:prodcategory.value,
            prodinfo:prodinfo.value,
            userid:userid.value
        }
        console.log(product);
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState == XMLHttpRequest.DONE){
                if(xhr.status == 200){
                    alert("상품 수정 성공!")
                    document.location.replace(`/get/${prodnum}`);
                }
                else{
                    alert("상품 등록 실패!");
                }
            }
        }
        xhr.open("PUT",`/prod/${prodnum}`)
        xhr.setRequestHeader("Content-Type","application/json;charset=utf-8");
        xhr.send(JSON.stringify(product));
    }

    const clickList = (e)=>{
        e.preventDefault();
        document.location.replace("/list")
    }

    useEffect(()=>{
        axios.get(`/prod/${prodnum}`)
        .then(resp => {
            setData(resp.data);
        })
    })

    const el = data?
    <>
        <Header loginUser={data.loginUser}></Header>
        <Modify
            data={data} clickModify={clickModify} clickList={clickList}
        />
        <Footer></Footer>
    </> :
    <>{prodnum} 로딩중...</>

    return el;
}
export default ModifyContainer;