<?php
require_once("conectare.php");
$nume=$_POST['nume'];
if(strcmp($nume,"")==0)
 {echo "Nu ati scris numele";
  require_once("date.html");
  exit;
}
$x1=$_POST['Buton1'];
if($x1==1) $x1=1; else $x1=0;
$x2=$_POST['Buton2'];
if($x2==2) $x2=1; else $x2=0;
$x3=$_POST['Buton3'];
if($x3==2) $x3=1; else $x3=0;
$x4=$_POST['Buton4'];
if($x4==1) $x4=1; else $x4=0;
$x5=$_POST['Buton5'];
if($x5==2) $x5=1; else $x5=0;
$x6=$_POST['Buton6'];
if($x6==1) $x6=1; else $x6=0;
$x7=$_POST['Buton7'];
if($x7==3) $x7=1; else $x7=0;
$x8=$_POST['Buton8'];
if($x8==1) $x8=1; else $x8=0;
$x9=$_POST['Buton9'];
if($x9==1) $x9=1; else $x9=0;
$S=$x1+$x2+$x3+$x4+$x5+$x6+$x7+$x8+$x9+1;
$cerere="INSERT INTO note (nume,rez) VALUES('$nume',$S)";
$rezultat=mysql_query($cerere);
if(!$rezultat)
echo mysql_errno().":".mysql_error();
?>
