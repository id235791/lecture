const client = require('mongodb').MongoClient;
// useUnifiedTopology : 통합모드
client.connect('mongodb://127.0.0.1:27017', {useUnifiedTopology: true}, (err, db) => {
    if(err){
        console.log(err);
    }else{
        console.log(`connected:${db}`);
        const database = db.db('frontend');
        const members = database.collection('member');
        members.insertMany([{'userid':'jamun','userpw':'aaaa','name':'천자문'}], (err, result) => {
            if(err){
                console.log(err);
            }else{
                console.log('성공!');
            }
        });
    }
});