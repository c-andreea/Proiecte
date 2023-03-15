<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Shop</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-betal/dist/css/bootstrap">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class= "container my-5">
<h2>List of Clients</h2>
<a class="btn btn-primary" href="/myshop/create.php" role="button">New Client</a>
<br>
<table  class="customers">
<thead>
<tr>
<th>ID</th>
<th>Nume</th>
<th>Email</th>
<th>Telefon</th>
<th>Adresa</th>
<th>Actiune</th>
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
$sql = "SELECT * FROM persoana";
$result = $connection->query($sql);
if (!$result) {
    die("Invalid query: ". $connection->error);
    }


    // read data of each row
while ($row = $result->fetch_assoc()) {
    echo "
    <tr>
    <td>$row[id_p]</td>
    <td>$row[nume]</td>
    <td>$row[email]</td>
    <td>$row[telefon]</td>
    <td>$row[adresa] </td>
    <td>
    <a class='btn btn-primary btn-sm' href='/myshop/edit.php?id'>Edit</a>
    <a class='btn btn-danger btn-sm' href='/myshop/delete.php'>Delete</a>
    </td>
    </tr>";}
    
?>
</thead>
</table>
</div>
</body>
</html>