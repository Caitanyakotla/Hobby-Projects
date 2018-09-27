<?php
//
$hostname="mysql153.secureserver.net";
$username="krishna";
$password="password";
$dbname="kotla";
$usertable="Employee";
$yourfield = "FirstName";

mysql_connect($hostname,$username, $password) OR DIE ("Unable to connect to database! Please try again later.");
mysql_select_db($dbname);

$query = "SELECT * FROM $usertable";
$result = mysql_query($query);
if($result) {
    while($row = mysql_fetch_array($result)){
        $name = $row["$yourfield"];
        echo "Name: ".$name;
    }
}
?>