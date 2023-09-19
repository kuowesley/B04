<?php
	$db_link=@mysqli_connect("localhost","root","0000");
	if(!$db_link){
		die("資料庫連線失敗");
	}else{
		
	}
	mysqli_query($db_link,"SET NAMES 'utf-8'");  //設定字元集與編碼為utf-8
	$seldb=@mysqli_select_db($db_link,"b04");
	if(!$seldb){
		die("資料庫選擇失敗");
	}else{

	}
?>