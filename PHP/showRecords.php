<?php
header('Content-Type: text/html; charset=utf-8');
$result = file_get_contents('php://input');

$ob=json_decode($result);
if(isset($ob)){
	session_id($ob->{"session"});
	session_start();

	$username=$_SESSION['un'];
$time =$ob->{"time"};
// session_id($ob->{"session"});
// session_start();
}


require("conn_mysql.php");
	

	$sql_query="SELECT * FROM record where account='$username'AND time LIKE '$time%'" ;
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗");
	$response=array();
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
			// $info["Name"]=$_SESSION["un"];
			$info["Name"]=$name; //食物名稱
			$info["Time"]=$time; //時間
			$info["Type"]=$type; //類型
			$info["Portion"]=$portion; //單一份量
			$infoe["Unit"]=$unit; //份數
			$info["Kal"]=$kal;   //熱量
			$info['Proteni']=$proteni; //蛋白質
			$info["Fat"]=$fat;   //脂肪
			$info["Carbohydrate"]=$carbohydrate; //碳水化合物
			$info["Na"]=$Na;     //鈉
			array_push($response,$info);
			
		}		

	
	$final =json_encode($response);
			echo $final;


	
?>

