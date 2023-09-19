<?php
header('Content-Type: text/html; charset=utf-8');
$result = file_get_contents('php://input');

$ob=json_decode($result);
if(isset($ob)){
	session_id($ob->{"session"});
	if(isset($ob->{"time"})){
		$time =$ob->{"time"};
	}
	else{
		$time='2020-10-17';
	}
	session_start();
	$username=$_SESSION['un'];
}


$mKal=0;
$lkal=0;
require("conn_mysql.php");
	
	$sql_query="SELECT * FROM record where account='$username'AND time LIKE '$time%'";
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗");
	$response=array();
	if(mysqli_num_rows($result1)>0){
		while($row=mysqli_fetch_array($result1)){
			
			$kal=$row['kal'];
			
			$mKal+=$kal;	
			
		}		

	}
	$sql_query="SELECT hopekal FROM userdata where account='$username'";
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗1");
	if(mysqli_num_rows($result1)){
		if($row=mysqli_fetch_array($result1)){
			$hopekal=$row['hopekal'];
		}

	}

	$lkal=$hopekal-$mKal;
	$sql_query="SELECT * FROM fooddata where kal <= '$lkal ' AND kal != 0";
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗2");
	$response=array();
	$i=0;
	
	mt_srand((double)microtime()*1000000);  //以時間當亂數種子


	$Rand = Array(); //定義為陣列


	$count = 5 ; //共產生幾筆


	for ($i = 1; $i <= $count; $i++) {


    $randval = mt_rand(1,46); 
    $num=mysqli_num_rows($result1);

     if (in_array($randval, $Rand)) { //如果已產生過迴圈重跑


        	$i--;


    }else{


        $Rand[] = $randval; //若無重復則 將亂數塞入陣列


    }
    

	}
	
	for($i=0;$i<$num;$i++){
		if($row=mysqli_fetch_array($result1)){
			if($i==$Rand[0]||$i==$Rand[1]||$i==$Rand[2]||$i==$Rand[3]||$i==$Rand[4]){
			$foodname=$row['name'];
			$type=urlencode($row['type']);
			$portion=$row['portion'];
			$unit=$row['unit'];
			$kal=$row['kal'];
			$proteni=$row['proteni'];
			$fat=$row['fat'];
			$carbohydrate=$row['carbohydrate'];
			$Na=$row['Na'];

			
			$info=array();
			$info["foodName"]=$foodname;
			$info["Type"]=$type; //類型
			$infoe["Portion"]=$portion; //單一份量
			$info["Unit"]=$unit; //份數
			$info["Kal"]=$kal;   //熱量
			$info['Proteni']=$proteni; //蛋白質
			$info["Fat"]=$fat;   //脂肪
			$info["Carbohydrate"]=$carbohydrate; //碳水化合物
			$info["Na"]=$Na;     //鈉
			$info["Lkal"]=$lkal;

			array_push($response,$info);

			}
		}
	}

	
	$final =json_encode($response);
			echo $final;

	
?>

