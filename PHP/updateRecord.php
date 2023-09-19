<?php

header('Content-Type: text/html; charset=utf-8');
require("conn_mysql.php");
	$result = file_get_contents('php://input');
	$object=json_decode($result);
	session_id($ob->{"session"});
	session_start();

	$account=$_SESSION['un'];
	$food =$object->{'foodName'};
	$type = $object->{"type"};
	$unit = $object->{"unit"};
	
	$times =$object->{"time"};
	$portion = (double)$object->{"portion"};
	$kal = (int)$object->{"kal"};
	$proteni =(double) $object->{"proteni"};
	$fat = (double)$object->{"fat"};
	$carbohydrate = (double)$object->{"carbohydrate"};
	$Na = (double)$object->{"Na"};
			
			$sql="UPDATE record SET name='$name' ,type='$type', portion=$portion,unit=$unit,kal=$kal,proteni=$proteni,fat=$fat,carbohydrate=$carbohydrate,Na=$Na ) WHERE account='$account'AND time=$times'";
			if ($db_link->query($sql) === TRUE) {
  				echo"成功";
				}
			else echo "幹你娘";
			
			
			

	// }
// }

	
?>

