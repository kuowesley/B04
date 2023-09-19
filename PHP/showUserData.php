<?php
header('Content-Type: text/html; charset=utf-8');
$result = file_get_contents('php://input');

$ob=json_decode($result);
if(isset($ob)){
	session_id($ob->{"session"});
	session_start();

	$username=$_SESSION['un'];

}

require("conn_mysql.php");
	
	$sql_query="SELECT * FROM userdata where account='$username'";
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗");
	if(mysqli_num_rows($result1)){
		if($row=mysqli_fetch_array($result1)){
			$nickname=$row['nickname'];
			$height=$row['height']*1;
			$weight=$row['weight']*1;
			$hopeweight=$row['hopeweight']*1;
			$hopekal=$row['hopekal']*1;
			
			$info=array();
			$info["username"]=$username;
			$info["nickname"]=$nickname; //暱稱
			$info["height"]=$height;  //身高
			$info["weight"]=$weight; //體重
			$info["hopeweight"]=$hopeweight; //理想體重
			$info['hopekal']=$hopekal; //每日熱量
			$final =json_encode($info);
			echo $final;
		}		

	}


	
?>

