<?php
    session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
</head>
<body>
<?php
    if(!isset($_SESSION['id'])){
?>
    <h2>로그인</h2>
    <form method="post" action="login_ok.php">
        <p>아이디 : <input type="text" name="userid"></p>
        <p>비밀번호 : <input type="password" name="userpw"></p>
        <p><input type="submit" value="로그인"></p>
    </form>
    <p>아직 회원이 아니신가요? <a href="./regist.php">회원가입</a>
<?php
    }else{
?>
    <p><?=$_SESSION['id']?>님 환영합니다.</p>
    <p><a href="./logout.php">로그아웃</a> | <a href="./board/list.php">회원게시판</a></p>
<?php
    }
?>
</body>
</html>