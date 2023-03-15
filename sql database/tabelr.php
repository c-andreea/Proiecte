<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-betal/dist/css/bootstrap">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class= "container my-5">


<br>
<table  class="customers">
<thead>
<tr>
<th>ID Cj</th>
<th>ID R</th>
<th>Data</th>
<th>Suma</th>

</tr>
<tbody>
<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "cabinet_avocatura";
// Create connection
$connection = new mysqli($servername, $username, $password, $database);
// Check connection
if ($connection->connect_error) {
die("Connection failed:". $connection->connect_error);

} 
$sql = "SELECT * FROM rata";
$result = $connection->query($sql);
if (!$result) {
    die("Invalid query: ". $connection->error);
    }


    // read data of each row
while ($row = $result->fetch_assoc()) {
    echo "
    <tr>
    <td>$row[id_cj]</td>
    <td>$row[id_r]</td>
    <td>$row[data]</td>
    <td>$row[suma]</td>

   
    </td>
    </tr>";}
    
?>
</thead>
</table>
</div>

</body>
</html>