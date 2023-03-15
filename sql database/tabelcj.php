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
<th>ID</th>
<th>Data</th>
<th>Obiect</th>
<th>Onorar</th>
<th>Numar Pagini</th>
<th>ID Cliect</th>
<th>ID Avocat</th>

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
$sql = "SELECT * FROM contract_j";
$result = $connection->query($sql);
if (!$result) {
    die("Invalid query: ". $connection->error);
    }


    // read data of each row
while ($row = $result->fetch_assoc()) {
    echo "
    <tr>
    <td>$row[id_cj]</td>
    <td>$row[data]</td>
    <td>$row[obiect]</td>
    <td>$row[onorar]</td>
    <td>$row[nr_pagini] </td>
     <td>$row[id_client] </td>
     <td>$row[id_avocat] </td>
  
    </td>
    </tr>";}
    
?>
</thead>
</table>
</div>

</body>
</html>