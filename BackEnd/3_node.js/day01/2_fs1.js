// require : 다른곳에 있는 모듈을 불러오는 함수
const fs = require('fs');
//비동기식으로 읽음
fs.readFile('./text1.txt','utf-8',(err,data)=>{
    if(err){
        console.log(err);
    }else{
        console.log(`비동기식으로 읽음 : ${data}`);
    }
});

//동기식으로 읽음
const text = fs.readFileSync('./text1.txt','utf-8');
console.log(`동기식으로 읽음 : ${text}`);

//파일 여는데 시간이 걸리니 비동기식은 파일 열면서 지나갔다가
//동기식 먼저 처리하고 다시 돌아와서 콜백함수로 결과 보여줌
