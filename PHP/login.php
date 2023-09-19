<?php
$result = file_get_contents('php://input');
$object=json_decode($result);
$username=$object->{'username'};
$password=$object->{'password'};
session_id($object->{'session'});
session_start();


require("conn_mysql.php");
	$sql_query_login="SELECT * FROM usercenter where account='$username' AND pw='$password'";
	$result1=mysqli_query($db_link,$sql_query_login) ;
	if(mysqli_num_rows($result1)){

		$response ="sc";
		$_SESSION['un']=$username;
	}

	else $response ="er";

	echo $response;

?>