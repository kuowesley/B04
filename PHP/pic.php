<?php

header('Content-Type: text/html; charset=utf-8');
	$result = file_get_contents('php://input');
	$object=json_decode($result);
	session_id("123");
	session_start();

	

	$pic =$object->{"pic"};
	$pic = str_replace('data:image/jpeg;base64,','', $pic);
	$data =base64_decode($pic);
	file_put_contents('D:\darknet\build\darknet\x64\_test\1.jpg', $data);
	$command1='activate darknet';
	$command2='D:\darknet\build\darknet\x64/model.bat';
	$command3='python';
	system($command2,$res);
	echo $res;

	
	


			
			
	
?>
