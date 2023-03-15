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
  Să se găsească perechi de contracte juridice (id_cj1, id_cj2) pentru clienți diferiți dar
același avocat. O pereche este unică în rezultat.
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

$query = "call interogare4B();";

$result = mysqli_query($conn, $query);

$contracts = mysqli_fetch_all($result, MYSQLI_ASSOC);

echo "<table>";
echo "<tr><th>id_cj1</th><th>id_cj2</th></tr>";
foreach ($contracts as $contract) {
    echo "<tr>";
    echo "<td>" . $contract['id_cj1'] . "</td>";
    echo "<td>" . $contract['id_cj2'] . "</td>";
    echo "</tr>";
}
echo "</table>";

mysqli_close($conn);

?>
  <a class="btn btn-primary" href="interogari.html" role="button">Inapoi</a>

</body>
</html>