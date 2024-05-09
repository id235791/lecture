<?php
    $name = $_GET["name"];
    $query = $_GET["q"];    // 오징어게임
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>get 방식</title>
</head>
<body>
    <h2>검색 결과</h2>
    <p>이름 : <?=$name?></p>
    <p>검색어 : <?=$query?></p>
</body>
</html>