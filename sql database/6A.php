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
  Să se găsească pentru fiecare nume de avocat valoarea medie a salariului pe
anul 2022.
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


$query = "call interogare6A();";


$result = mysqli_query($conn, $query);


$persons = mysqli_fetch_all($result, MYSQLI_ASSOC);


echo "<table>";
echo "<tr><th>Nume</th><th>AVG(onorar)</th></tr>";
foreach ($persons as $person) {
    echo "<tr>";
    echo "<td>" . $person['nume'] . "</td>";
    echo "<td>" . $person['AVG(cj.onorar)'] . "</td>";
    echo "</tr>";
}
echo "</table>";


mysqli_close($conn);

?>
  <a class="btn btn-primary" href="interogari.html" role="button">Inapoi</a>

</body>
</html>