<?php
	
	ob_start();
	session_start();
	require_once 'dbconnect.php';
	
	// if session is not set this will redirect to login page
	if( !isset($_SESSION['user']) ) {
		header("Location: index.php");
		exit;
	}
	// select loggedin users detail
	$userId=$_SESSION['user'];
	$res=mysql_query("SELECT * FROM users WHERE userId!=$userId");
	$userRow=mysql_fetch_array($res);
	//insert into database
	if($_SERVER['REQUEST_METHOD']=='POST')
	
{    
    $file=$_POST['file'];
	$sender=$_POST['sender'];
	
		if(!empty($file)&&!empty($sender))
{
	
    $SQL ="INSERT INTO can_view(sender,reciver,file_id) VALUES ('$userId','$sender','$file')";
        mysql_real_escape_string($SQL);
        $result = mysql_query($SQL) or die (mysql_error());
	
}
		
	}

?>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Coding Cage - Login & Registration System</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css"  />
<link rel="stylesheet" href="style.css" type="text/css" />

</head>
<body>
<form   method="POST" action="test.php">
<?php
  $query = "SELECT * from users where userId!=$userId";
$result = mysql_query($query) or die(mysql_error()."[".$query."]");?>

    <select name='sender'>
<?php while ($row = mysql_fetch_array($result)){
?>
   <option  value=" <?php  echo $row['userId']; ?> ">
     <?php echo $row['userName']; ?>
    </option>
<?php
}
?>        
</select>
 <br>
 <br>
 <?php
  $query = "SELECT * from tbl_uploads where userId=$userId";
$result = mysql_query($query) or die(mysql_error()."[".$query."]");?>

    <select  name="file" >
<?php while ($row = mysql_fetch_array($result)){
?>
   <option value=" <?php  echo $row['id']; ?> ">
     <?php echo $row['file'] ; ?>
    </option>
<?php
}
?>        
</select>
<br>
<br>
  <input type="submit" value="submit" >
</form>

</body>

