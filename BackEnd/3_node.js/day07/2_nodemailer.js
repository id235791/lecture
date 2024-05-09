const nodemailer = require('nodemailer');

const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: 'id235791@gmail.com',
        pass: 'mguhbesibrtvxjpx'
    },
    host: 'smtp.mail.com',
    port: '465'
});

const mailOption = {
    from: '정다솔<id235791@gmail.com>',
    to: '정다솔<id235761@gmail.com>',
    subject: 'node.js로 보내는 첨부메일',
    html: '<h2>안녕하세요. node.js로 보내는 첨부메일입니다</h2><p style="font-size: 15px; color: deeppink;">파일이 잘 저장되었나요?</p>',
    attachments: [
        {
            filename: '메일아이콘.png',
            path: '2561462_mail_icon.png',
        }
    ]
};

transporter.sendMail(mailOption, (err, info) => {
    if(err){
        console.log(err);
    }else{
        console.log(info);
    }
    transporter.close();
});
