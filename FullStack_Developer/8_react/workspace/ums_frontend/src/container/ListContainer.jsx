import { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import axios from 'axios';
import List from "../components/List";
import Header from "../components/layout/Header";
import Footer from "../components/layout/Footer";

const ListContainer = () => {
    const [data,setData] = useState();
    const [pagenum,setPagenum] = useState('1');
    const [category,setCategory] = useState('none');

    const changePage = (e) => {
        e.preventDefault();
        let target = e.target.getAttribute("href");
        setPagenum(target);
    }
    const changeCategory = (value) => {
        setCategory(value);
        setPagenum(1);
    }
    const clickProduct = (e) => {
        let prodnum = e.target.parentNode.children[0].innerHTML;
        document.location.href=`/get/${prodnum}`
    }

    useEffect(()=>{
        axios.get(`/prod/list/${pagenum||'1'}/${category}`)
        .then(resp => {
            console.log("data",resp.data);
            setData(resp.data);
        })
    },[pagenum,category])

    const el = data?
    <>
        <Header loginUser={data.loginUser}/>
        <List list={data.list} page={data.page} changePage={changePage} changeCategory={changeCategory} clickProduct={clickProduct}/>
        <Footer></Footer>
    </> :
    <>로딩중...</>
    return el;
}
export default ListContainer;