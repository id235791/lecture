const http = require("http");
const fs = require("fs");

http.createServer((req,resp) => {
    fs.readFile('./banana.png',(err,data)=>{
        if(err){
            console.log(err);
        }else{
            resp.writeHead(200,{'content-type':'image/png'})
            resp.end(data);
        }
    })

}).listen(3000,()=>{
    console.log('3000번 포트로 이미지 서버 실행중 ...');
})

http.createServer((req,resp) => {
    fs.readFile('./savage.mp3',(err,data)=>{
        if(err){
            console.log(err);
        }else{
            resp.writeHead(200,{'content-type':'audio/mp3'})
            resp.end(data);
        }
    });
}).listen(4000,()=>{
    console.log('4000번 포트로 오디오 서버 실행중 ...');
})