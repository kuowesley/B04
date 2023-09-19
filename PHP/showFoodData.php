<?php
header('Content-Type: text/html; charset=utf-8');
$result = file_get_contents('php://input');

$ob=json_decode($result);
if(isset($ob)){
$foodname=$ob->{"foodName"};
$amount=$ob->{"amount"};

}
else{
	$foodname="牛排";
	$amount="2";
	echo "fuck";
}

	$time =date("Y-m-d H:i:s");
	$howmuch=(double)$amount;
require("conn_mysql.php");
	

	

	$sql_query="SELECT * FROM fooddata where name='$foodname'";
	$result1=mysqli_query($db_link,$sql_query) or die("查詢失敗");
	if(mysqli_num_rows($result1)){
		if($row=mysqli_fetch_array($result1)){
			$type=urlencode($row['type']);
			$portion=$row['portion']*$howmuch;
			$unit=$row['unit'];
			$kal=$row['kal']*$howmuch;
			$proteni=$row['proteni']*$howmuch;
			$fat=$row['fat']*$howmuch;
			$carbohydrate=$row['carbohydrate']*$howmuch;
			$Na=$row['Na']*$howmuch;

			
			$info=array();
			$info["Type"]=$type; //類型
			$info["Portion"]=$portion; //單一份量
			$info["Unit"]=$unit; //份數
			$info["Kal"]=$kal;   //熱量
			$info['Proteni']=$proteni; //蛋白質
			$info["Fat"]=$fat;   //脂肪
			$info["Carbohydrate"]=$carbohydrate; //碳水化合物
			$info["Na"]=$Na;     //鈉
			$final =json_encode($info);
			echo $final;
		}		

	}
	else echo "fail";


	
?>

