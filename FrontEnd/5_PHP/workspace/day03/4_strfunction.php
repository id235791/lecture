<?php
    $str1 = "abcdefghijklmn12345";
    $str2 = "가나다라마바사아자차카타파하";
    $str3 = "Hello/PHP/Hello/World";
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문자열 함수</title>
</head>
<body>
    <h2>문자열 함수</h2>
<?php
    echo strlen($str1)."<br>"; 
    echo strlen($str2)."<br>";
    echo "<br>";

    echo strcmp("abc", "ABC")."<br>";
    echo strcmp("10", "2")."<br>";
    echo strcmp("abc", "abc")."<br>";
    echo "<br>";

    echo "strstr : ".strstr($str1, "cd")."<br>";
    echo "<br>";

    echo 'strpos : '.strpos($str1, "cd")."<br>";
    echo "<br>";

    echo substr($str1, 3)."<br>";
    echo substr($str1, -3)."<br>";
    echo substr($str1, 3, 10)."<br>";

    $arr = explode("/", $str3);
    foreach($arr as $str){
        echo $str." ";
    }
    echo("<br>");
    echo str_replace("o", "🎃", $str3)."<br>";
?>
</body>
</html>