const express = require('express');
const bp = require('body-parser');
const app = express();
const router = express.Router();

app.use(bp.urlencoded({extended:false}));
router.route('/member/login').post((req,resp)=>{
    console.log('/member/login 호출!');
})
router.route('/member/regist').post((req,resp)=>{
    console.log('/member/regist 호출!');
})
router.route('/about/detail').get((req,resp)=>{
    console.log('/about/detail 호출!');
})

app.use('/',router);
app.all('*',(req,resp)=>{
    resp.status(404).send('<h2>페이지를 찾을 수 없습니다.</h2>');
});

app.listen(3000,()=>{
    console.log('3000번 포트로 서버 실행중 ...');
})