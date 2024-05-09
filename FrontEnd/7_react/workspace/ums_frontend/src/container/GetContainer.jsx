import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Get from "../components/Get";
import Header from "../components/layout/Header";
import Footer from "../components/layout/Footer";

const GetContainer = () => {
    const {prodnum} = useParams();
    const [data,setData] = useState();
    const clickList = (e)=>{
        e.preventDefault();
        document.location.replace("/list")
    }
    const clickLike = (e) => {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4 && xhr.status == 200){
                let likecnt = Number(e.target.nextElementSibling.innerHTML);
                likecnt++;
                e.target.nextElementSibling.innerHTML = likecnt;
            }
        }
        xhr.open("PATCH",`/prod/like/${prodnum}`);
        xhr.send();
        
    }
    const clickModify = (e) => {
        e.preventDefault();
        document.location.href = `/modify/${prodnum}`
    }
    const clickRemove = (e) => {
        e.preventDefault();
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4 && xhr.status == 200){
                alert("상품 삭제 완료!")
                document.location.replace("/list")
            }
        }
        xhr.open("DELETE",`/prod/${prodnum}`);
        xhr.send();
    }
    useEffect(()=>{
        axios.get(`/prod/${prodnum}`)
        .then(resp => {
            setData(resp.data);
        })
    },[])

    const el = data?
    <>
        <Header loginUser={data.loginUser}></Header>
        <Get
            data={data.product} clickList={clickList}
            clickLike={clickLike} loginUser={data.loginUser}
            clickModify={clickModify} clickRemove={clickRemove}
        />
        <Footer></Footer>
    </>
    :
    <>{prodnum} 상품 로딩중...</> ;

    return el;
}
export default GetContainer;