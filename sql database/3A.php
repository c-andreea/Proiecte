<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
  <style>
   
  </style>
</head>
<body>
 <!----<div class="bg-image"></div>---->
  <!--  <div class="blu"></div>--->

  <p style='color: white; font-size: 20px; margin: 20px;margin-top: 50px;'>
  Să se găsească detaliile pentru contractele de asistență juridică din luna octombrie 2022
ce au onorar cuprins între 900 și 1500 în ordine crescătoare a datei.</p>

  <?php

$host = "localhost";
$user = "root";
$password = "";
$dbname = "cabinet_avocatura";

$conn = mysqli_connect($host, $user, $password, $dbname);


if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$query = "call interogare3A();";

$result = mysqli_query($conn, $query);

$contracts = mysqli_fetch_all($result, MYSQLI_ASSOC);

foreach ($contracts as $contract) {
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>ID: " . $contract['id_cj'] . "</p>";
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>Data: " . $contract['data'] . "</p>";
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>ONORAR: " . $contract['onorar'] . "</p>";
}

mysqli_close($conn);

?>
  <a class="btn btn-primary" href="interogari.html" role="button">Inapoi</a>


</body>
</html>