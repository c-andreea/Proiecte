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
  Să se găsească detaliile contractelor de muncă pentru funcțiile ce încep cu litera ‘a’ în
ordine descrescătoare a salariului de bază și crescătoare după funcție.
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

$query = "call interogare3B();";

$result = mysqli_query($conn, $query);

$contracts = mysqli_fetch_all($result, MYSQLI_ASSOC);

echo "<table>";

foreach ($contracts as $contract) {
    echo "<tr>";
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>ID: " . $contract['id_cm'] . "</p>";
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>functie: " . $contract['functie'] . "</p>";
    echo "<p style='color: white; font-size: 17px; margin: 15px;'>salar_baza: " . $contract['salar_baza'] . "</p>";
    echo "</tr>";
}
echo "</table>";

mysqli_close($conn);

?>

<a class="btn btn-primary" href="interogari.html" role="button">Inapoi</a>
</body>
</html>