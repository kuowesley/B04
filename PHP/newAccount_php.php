<?php 
require("conn_mysql.php");


	$result = file_get_contents('php://input');
	$object=json_decode($result);
	session_id($object->{"session"});
	session_start();

	$username=$object->{"username"};
	$password=$object->{"password"};
	$_SESSION['un']=$username;

	
		$sql = "INSERT INTO usercenter(account,pw) VALUES ('$username','$password')";
			# 執行 MySQL/MariaDB 指令
		if ($db_link->query($sql) === TRUE ) ;
		else echo "幹你娘";

		$sql2= "INSERT INTO userdata(account)VALUES('$username')";
		if ($db_link->query($sql2) === TRUE ) ;
				else echo "幹你娘";
	
	# MySQL/MariaDB 指令
	

	
	


?>

