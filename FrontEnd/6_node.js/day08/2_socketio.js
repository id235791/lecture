const express = require('express');
const logger = require('morgan');
const static = require('serve-static');
const path = require('path');
const socketio = require('socket.io');

const app = express();

app.use(logger('dev'));
app.use('/public', static(path.join(__dirname, 'public')));

const server = app.listen(3000, () => {
    console.log('3000번 포트로 서버 실행중 ...');
});

const io = socketio(server,{
    //보안정책 https://면 괜찮, 안쓸시 서로다른 주소나 포트로 데이터를 동시에 보낼 때 발생(브라우저가 막음)
    //내부적으로 Ajax로 바꾸어줌
    cors:{
        origin:"*",
        methods:["GET","POST"]
    }
});

io.sockets.on('connection', (socket) => {
    //_peername : 클라이언트의 정보
    console.log(`connection : ${socket.request.connection._peername}`);
    socket.remoteAddress = socket.request.connection._peername.address;
    //::랜카드이름:IP주소
    console.log(`socket.remoteAddress : ${socket.remoteAddress}`);
    socket.remotePort = socket.request.connection._peername.port;
    console.log(`socket.remotePort : ${socket.remotePort}`);

    //메세지 이벤트 핑퐁
    socket.on('message', function(message){
        console.log(`message 이벤트를 받았습니다`);
        console.dir(message);
        io.sockets.emit('message', message);
    });
});




