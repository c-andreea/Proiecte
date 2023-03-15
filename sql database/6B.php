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
  Să se găsească contractele juridice care nu au fost achitate în întregime.
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

$query = "SELECT cj.id_cj FROM Contract_j cj WHERE NOT EXISTS (SELECT * FROM Rata r WHERE cj.id_cj = r.id_cj AND r.suma >= cj.onorar)";

$result = mysqli_query($conn, $query);

$contracts = mysqli_fetch_all($result, MYSQLI_ASSOC);

foreach ($contracts as $contract) {
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>id: ".$contract['id_cj'] . "</p>";
}

mysqli_close($conn);

?>
  <a class="btn btn-primary" href="interogari.html" role="button">Inapoi</a>

</body>
</html>