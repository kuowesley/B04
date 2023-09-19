<?php
header('Content-Type: text/html; charset=utf-8');
$result = file_get_contents('php://input');

$ob=json_decode($result);
if(isset($ob)){
session_id($ob->{"session"});
	session_start();

	$username=$_SESSION['un'];
$time =$ob->{"time"};
}
$response=array();
require("conn_mysql.php");
	
	$sql_query="SELECT * FROM record where account='$username' AND time LIKE '$time%' ";
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗");
	$response=array();
	$response["Kal"]=0;
	$response["Proteni"]=0;
	$response["Fat"]=0;
	$response["Carbohydrate"]=0;
	$response["Na"]=0;
	while($row=mysqli_fetch_array($result1)){
		
			$name=$row['name'];
			$time=$row['time'];
			$type=urlencode($row['type']);
			$portion=$row['portion'];
			$unit=$row['unit'];
			$kal=$row['kal'];
			$proteni=$row['proteni'];
			$fat=$row['fat'];
			$carbohydrate=$row['carbohydrate'];
			$Na=$row['Na'];					
			$info=array();
			
			$info["Kal"]=$kal;   //熱量
			$info['Proteni']=$proteni; //蛋白質
			$info["Fat"]=$fat;   //脂肪
			$info["Carbohydrate"]=$carbohydrate; //碳水化合物
			$info["Na"]=$Na;     //鈉	

			$response["Kal"]+=$info["Kal"];
			$response["Proteni"]+=$info["Proteni"];
			$response["Fat"]+=$info["Fat"];
			$response["Carbohydrate"]+=$info["Carbohydrate"];
			$response["Na"]+=$info["Na"];
		}		
	$final =json_encode($response);
			echo $final;