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
$res=array();
require("conn_mysql.php");
	$time0=$time."0";
	$time1=$time."1";
	$time2=$time."2";
	$time3=$time."3";
	
	$sql_query="SELECT * FROM record where account='$username' AND ( time LIKE '$time0%' OR time LIKE '$time1%' OR time LIKE '$time2%' OR time LIKE'$time3%')";
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗");
	$res=array();
	
	$res["Kal"]=0;
	$res["Proteni"]=0;
	$res["Fat"]=0;
	$res["Carbohydrate"]=0;
	$res["Na"]=0;

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

			$res["Kal"]+=$info["Kal"];
			$res["Proteni"]+=$info["Proteni"];
			$res["Fat"]+=$info["Fat"];
			$res["Carbohydrate"]+=$info["Carbohydrate"];
			$res["Na"]+=$info["Na"];
		}		
	$final =json_encode($res);
			echo $final;