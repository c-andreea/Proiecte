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
  Să se găsească contractele de muncă cu același comision ca și alte contracte
de muncă.
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

$query = "call interogare5A();";

$result = mysqli_query($conn, $query);

$contracts = mysqli_fetch_all($result, MYSQLI_ASSOC);

echo "<table>";
echo "<tr><th>ID</th><th>Comision</th></tr>";
foreach ($contracts as $contract) {
    echo "<tr>";
    echo "<td>" . $contract['id_cm'] . "</td>";
    echo "<td>" . $contract['comision'] . "</td>";
    echo "</tr>";
}
echo "</table>";

mysqli_close($conn);

?>
  <a class="btn btn-primary" href="interogari.html" role="button">Inapoi</a>

</body>
</html>