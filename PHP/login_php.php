<?php
	session_start();
	header('Content-Type: text/html; charset=utf-8');
	
		
	
	
	$username=$_POST['username'];
	$password=$_POST['password'];
	$_SESSION['loginusername']=$_POST['username'];
	$_SESSION['loginpassword']=$_POST['password'];
	
	
		
		
		
	
	require("conn_mysql.php");
	$sql_query_login="SELECT * FROM usercenter where account='$username' AND pw='$password'";
	$result1=mysqli_query($db_link,$sql_query_login) or die("查詢失敗");
	if(mysqli_num_rows($result1)){
		$sql_query="SELECT * FROM record where account='$username'";
		$result=mysqli_query($db_link,$sql_query);
		echo "<table border=1 width=400 cellpadding=5>";
		echo "<tr>
			<td>時間</td>
			<td>食物名稱</td>
			<td>食物類型</td>
			<td>份量</td>
			<td>單位</td>
			<td>卡路里</td>
			<td>蛋白質</td>
			<td>脂肪</td>
			<td>碳水化合物</td>
			<td>鈉</td>
		      </tr>";
	
		while($row=mysqli_fetch_array($result)){
			
			echo "<tr>
				<td>$row[1]</td> 
				<td>$row[2]</td>
				<td>$row[3]</td>
				<td>$row[4]</td>
				<td>$row[5]</td>
				<td>$row[6]</td>
				<td>$row[7]</td>
				<td>$row[8]</td>
				<td>$row[9]</td>
				<td>$row[10]</td>
			      </tr>";
			
		}
		echo"</table>";
	}else{
		echo"登入失敗";
	}
?>
	<html>
	<input type="button" value="返回登入頁面" onclick="location.href='http://25.20.61.234:3307/login.html'">
	<input type="button" value="新增紀錄" onclick="location.href='http://25.20.61.234:3307/newrecord.html'">
	</html>
	
