<?php
header('Content-Type: text/html; charset=utf-8');
$result = file_get_contents('php://input');

$ob=json_decode($result);
if(isset($ob)){
session_id($ob->{"session"});
session_start();
if(isset($_SESSION['un'])){
	unset($_SESSION['un']);
}

}




	
?>

