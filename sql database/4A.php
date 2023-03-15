<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
  <style>
   
  </style>
</head>
<body>
 <div class="bg-image"></div>
  <!--  <div class="blu"></div>--->
  <p style='color: white; font-size: 20px; margin: 20px;margin-top: 50px;'>
  Să se găsească numele angajaților cu data de angajare în anul 2022 și care nu sunt
avocați.
</p>
  <?php

$host = "localhost";
$user = "root";
$password = "";
$dbname = "cabinet_avocatura";

$conn = mysqli_connect($host, $user, $password, $dbname);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$query = "call interogare4A();";

$result = mysqli_query($conn, $query);


$persons = mysqli_fetch_all($result, MYSQLI_ASSOC);


foreach ($persons as $person) {
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>nume: ".$person['nume'] ."</p>";
}


mysqli_close($conn);

?>
  <a class="btn btn-primary" href="interogari.html" role="button">Inapoi</a>
</body>
</html>