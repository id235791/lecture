const express = require('express');
const app = express();
app.use((req,resp)=>{
    console.log("첫번째 미들웨어 실행!");
    console.dir(req.header);
    const userAgent = req.header('User-Agent');
    console.log(userAgent);

    resp.writeHead(200,{'content-type':'text/html;charset=utf-8'});
    resp.write('<h2>익스프레스 서버에서 응답한 메세지입니다.</h2>');
    resp.write(`<p>user-agent:${userAgent}`);

    const userid=req.query.userid;
    resp.write(`<p>아이디:${userid}`);
    
    // 작성했던 바디 날리기
    resp.end();
}).listen(3000,()=>{
    console.log('3000번 포트로 서버 실행중 ...');
})