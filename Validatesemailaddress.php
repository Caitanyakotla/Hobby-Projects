HTML>
<BODY>
<H2>Regular Expression Tester</H2>
<FORM METHOD="POST" ACTION="RegularExpressionTester.php">
<FONT FACE="Courier">
String:
<BR>
<INPUT TYPE="TEXT" NAME="string">
<BR><BR>
Regular Expression:
<BR>
<INPUT TYPE="TEXT" SIZE=64 NAME="pattern" 
       VALUE="^([_a-zA-Z0-9-]+)@([a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*)$">
<BR><BR>
<BR>
<INPUT TYPE="SUBMIT">
</FONT>
</FORM>
</BODY>
</HTML>


<!-- RegularExpressionTester.php
<?php
echo "<BR><B>string:</B>$string";
echo "<BR><B>regular expression:</B>&nbsp;$pattern";
if (get_magic_quotes_gpc()){
    echo "<BR><BR>";
    echo "<BR>Stripping magic quotes....";
    $string = stripslashes($string);
    $pattern = stripslashes($pattern);
    echo "<BR><B>string:</B>&nbsp;$string";
    echo "<BR><B>regular expression:</B>&nbsp;$pattern";
}
$found = ereg($pattern, $string, $matches);
echo "<BR><BR>";
if ($found)
{
    echo "<BR><B>valid:</B>&nbsp;true";
    echo "<BR><BR>";
    echo "<BR><B>Components:&nbsp</B>";
    for ($i = 0; $i < count($matches); $i++)
    {
        echo "<BR>$matches[$i]";
    }
}
else
    echo "<BR><B>valid:</B>&nbsp;false";
?>
-->