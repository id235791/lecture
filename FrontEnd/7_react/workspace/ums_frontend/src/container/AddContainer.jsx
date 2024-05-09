import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from 'axios';
import Header from "../components/layout/Header";
import Add from "../components/Add";
import Footer from "../components/layout/Footer";

const AddContainer = () => {
    const [data,setData] = useState();

    const clickAdd = (e) => {
        e.preventDefault();
        const addForm = document.addForm;
        
        const prodname = addForm.prodname;
        if(prodname.value == ""){
            alert("상품명을 입력해주세요!");
            prodname.focus();
            return;
        }

        const prodprice = addForm.prodprice;
        if(prodprice.value == ""){
            alert("상품가격을 입력해주세요!")
            prodprice.focus();
            return;
        }

        const prodamount = addForm.prodamount;
        if(prodamount.value == ""){
            alert("상품수량을 입력해주세요!")
            prodamount.focus();
            return;
        }

        const prodcategory = addForm.prodcategory;
        if(prodcategory.value == ""){
            alert("카테고리를 선택해주세요!");
            return;
        }

        const prodinfo = addForm.prodinfo;
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

        const userid = addForm.userid;

        const product = {
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
                    alert("상품 등록 성공!")
                    document.location.replace("/list");
                }
                else{
                    alert("상품 등록 실패!");
                }
            }
        }
        xhr.open("POST","/prod/add")
        xhr.setRequestHeader("Content-Type","application/json;charset=utf-8");
        xhr.send(JSON.stringify(product));
    }

    const clickList = (e)=>{
        e.preventDefault();
        document.location.replace("/list")
    }
    useEffect(()=>{
        axios.get(`/prod/add`)
        .then(resp => {
            console.log("data",resp.data);
            setData(resp.data);
        })
    },[])

    const el = data?
    <>
        <Header loginUser={data.loginUser}/>
        <Add data={data} clickAdd={clickAdd} clickList={clickList}/>
        <Footer></Footer>
    </> :
    <>로딩중...</>
    return el;
}
export default AddContainer;