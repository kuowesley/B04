<?php

header('Content-Type: text/html; charset=utf-8');
require("conn_mysql.php");
	$result = file_get_contents('php://input');
	$object=json_decode($result);
	session_id("123");
	session_start();

	
	$account=$_SESSION['un'];
	$food =$object->{"foodname"};
	$type = $object->{"type"};
	$unit = $object->{"unit"};
	date_default_timezone_set('Asia/Taipei');
	$times = date("Y-m-d H:i:s");
	$portion = (double)$object->{"portion"};
	$kal = (int)$object->{"kal"};
	$proteni =(double) $object->{"proteni"};
	$fat = (double)$object->{"fat"};
	$carbohydrate = (double)$object->{"carbohydrate"};
	$Na = (double)$object->{"Na"};
			
			$sql="INSERT INTO record(account,time,name,type,portion,unit,kal,proteni,fat,carbohydrate,Na) VALUES ('$account','$times','$food','$type','$portion','$unit','$kal','$proteni','$fat','$carbohydrate','$Na')";
			if ($db_link->query($sql) === TRUE) {
  				echo"成功";
				}
			else echo "幹你娘";
			
			
			

	// }
// }

	
?>

