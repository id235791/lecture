const express = require('express');
const bodyparser = require('body-parser');
const logger = require('morgan');
const mongoose = require('mongoose');

const app = express();
const router = express.Router();

app.use(bodyparser.urlencoded({extended: false}));
app.use(logger('dev'));

let database;
let UserSchema;
let UserModel;

// http://127.0.0.1:3000/regist (post)
router.route('/regist').post((req, res) => {
    const userid = req.body.userid;
    const userpw = req.body.userpw;
    const name = req.body.name;
    const gender = req.body.gender;

    console.log(`userid:${userid}, userpw:${userpw}, name:${name}, gender:${gender}`);

    if(database){
        joinUser(userid, userpw, name, gender, (err, result) => {
            if(err){
                res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                res.write('<h2>회원가입 실패</h2>');
                res.end();
            }else{
                if(result){
                    res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                    res.write('<h2>회원가입 성공</h2>');
                    res.end();
                }else{
                    res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                    res.write('<h2>회원가입 실패</h2>');
                    res.end();
                }
            }
        });
    }else{
        res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
        res.write('<h2>데이터 베이스 연결 실패</h2>');
        res.end();
    }
});

// http://127.0.0.1:3000/login (post)
router.route('/login').post((req, res) => {
    const userid = req.body.userid;
    const userpw = req.body.userpw;

    console.log(`userid:${userid}, userpw:${userpw}`);

    if(database){
        loginUser(userid, userpw, (err, result) => {
            if(err){
                res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                res.write('<h2>로그인 실패</h2>');
                res.end();
            }else{
                if(result){
                    const name = result[0].name;
                    const gender = result[0].gender;
                    res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                    res.write('<p>회원 정보</p>');
                    res.write(`<p>아이디: ${userid}</p>`);
                    res.write(`<p>이름: ${name}</p>`);
                    res.write(`<p>성별: ${gender}</p>`);
                    res.end();
                }else{
                    res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                    res.write('<h2>로그인 실패</h2>');
                    res.end();
                }
            }
        });
    }else{
        res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
        res.write('<h2>데이터 베이스 연결 실패</h2>');
        res.end();
    }
});


// http://127.0.0.1:3000/list (get)
router.route('/list').get((req, res) => {
    if(database){
        UserModel.findAll((err, result) => {
            if(err){
                console.log('리스트 조회 실패');
            }else{
                if(result){
                    res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                    res.write('<h2>회원 리스트</h2>');
                    res.write('<div><ul>');

                    for(let i=0; i<result.length; i++){
                        const userid = result[i].userid;
                        const name = result[i].name;
                        const gender = result[i].gender;
                        res.write(`<li>${i} : ${userid} / ${name} / ${gender}</li>`);
                    }

                    res.write('</div></ul>');
                    res.end();
                }else{
                    res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
                    res.write('<h2>회원 정보가 없음</h2>');
                    res.end();
                }
            }
        });
    }else{
        res.writeHead('200', {'content-type':'text/html;charset=utf-8'});
        res.write('<h2>데이터 베이스 연결 실패</h2>');
        res.end();
    }
});





const joinUser = function(userid, userpw, name, gender, callback){
    const users = new UserModel({userid:userid, userpw:userpw, name:name, gender:gender});
    users.save((err, result) => {
        if(err){
            console.log(err);
            callback(err, null);
            return;
        }else{
            callback(null, result);
        }
    });
}

const loginUser = function(userid, userpw, callback){
    UserModel.find({userid:userid, userpw:userpw}, (err, result) => {
        if(err){
            console.log(err);
            callback(err, null);
            return;
        }else{
            if(result.length > 0){
                console.log('일치하는 사용자가 있음');
                callback(null, result);
            }else{
                console.log('일치하는 사용자가 없음');
                callback(null, null);
            }
        }
    });
}




function connectDB(){
    const uri = 'mongodb://127.0.0.1:27017/frontend';
    //전체 웹사이트에서 디비 처리를 동기화로 설정
    mongoose.Promise = global.Promise;
    //useNewUrlParser
    mongoose.connect(uri, {useNewUrlParser: true, useUnifiedTopology: false});
    database = mongoose.connection;
    database.on('error', console.error.bind(console, 'mongoose 연결 실패!'));
    database.on('open', () => {
        console.log('데이터베이스 연결 성공!');
        //document 삽입시 일정한 데이터가 들어가도록 조건을 걸어주는것
        UserSchema = mongoose.Schema({
            userid:String,
            userpw:String,
            name:String,
            gender:String
        });
        //findAll 이라는 사용자 메소드 설정
        UserSchema.static('findAll', function(callback){
           return this.find({}, callback); 
        });

        console.log('UserSchema 생성 완료!');
        // DAO 느낌
        UserModel = mongoose.model('user', UserSchema);
        console.log('UserModel이 정의됨!');
    });
}

app.use('/', router);

app.listen(3000, () => {
    console.log('3000번 포트로 서버 실행중 ...');
    connectDB();
});
