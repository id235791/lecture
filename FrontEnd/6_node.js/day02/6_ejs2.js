const express = require('express');
const bp = require('body-parser');
const fs = require('fs');
const ejs = require('ejs');

const app = express();
const router = express.Router();

app.use(bp.urlencoded({extended:false}));
router.route('/login').post((req,resp)=>{
    const userinfo = {userid:'apple',userpw:'abcd1234'};
    fs.readFile('./ejs2.ejs','utf-8',(err,data)=>{
        if(err){
            console.log(err)
        }else{
            resp.writeHead(200,{'content-type':'text/html'});
            resp.end(ejs.render(data,userinfo));
        }
    })
})
app.use('/',router);
app.all('*',(req,resp)=>{
    resp.status(404).send('<h2>페이지를 찾을 수 없습니다.</h2>');
});

app.listen(3000,()=>{
    console.log('3000번 포트로 서버 실행중 ...');
})