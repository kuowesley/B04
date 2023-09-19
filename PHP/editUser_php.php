<?php 
	header('Content-Type: text/html; charset=utf-8');
	
	$server = "127.0.0.1";         # MySQL/MariaDB 伺服器
	$dbuser = "root";       # 使用者帳號
	$dbpassword = "0000"; # 使用者密碼
	$dbname = "b04";    # 資料庫名稱

# 連接 MySQL/MariaDB 資料庫
	$connection = new mysqli($server, $dbuser, $dbpassword, $dbname);

# 檢查連線是否成功
if ($connection->connect_error) {
  die("連線失敗：" . $connection->connect_error);
}
	

	$result = file_get_contents('php://input');
	$object=json_decode($result);
	session_id('123');
	session_start();
	$account=$_SESSION['un'];
	$height=$object->{'height'};
	$nickname=$object->{'nickname'};
	$weight=$object->{'weight'};
	$hopeweight=($height/100)*($height/100)*22;
	$hopekal=$hopeweight*30;
	


	# MySQL/MariaDB 指令
	$sqlQuery= "UPDATE userdata SET nickname='$nickname',height='$height',weight='$weight',hopeweight='$hopeweight',hopekal='$hopekal' WHERE account='$account'";
	# 執行 MySQL/MariaDB 指令
	if ($connection->query($sqlQuery) === TRUE) {
  		echo"水啦";
  	

	}
	else echo "幹你娘";
		 

?>







