<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="style.css">
   
  <style>
   
  </style>
</head>
<body>
 <div class="bg-image"></div>
  <!--  <div class="blu"></div>--->
  <div class="tab">
            <button class="tablinks" onclick="openCity(event, 'Persoana')">Persoana</button>
            <button class="tablinks" onclick="openCity(event, 'Contracte munca')">Contracte munca</button>
            <button class="tablinks" onclick="openCity(event, 'Contracte juridice')">Contracte juridice</button>
            <button class="tablinks" onclick="openCity(event, 'Rata')">Rata</button>
          </div>
          
          <div id="Persoana" class="tabcontent">
          <div class= "container my-5">



<a class="btn btn-primary" href="http://127.0.0.1/tabelpers.php" role="button" target="_blank">Deschide in fila noua</a>
<br>
<table  class="customers">
<thead>
<tr>
<th>ID</th>
<th>Nume</th>
<th>Email</th>
<th>Telefon</th>
<th>Adresa</th>

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
   
    </td>
    </tr>";}
    
?>
</thead>
</table>
</div>
            </div>
          
          <div id="Contracte munca" class="tabcontent">
          <div class= "container my-5">
         

<a class="btn btn-primary" href="http://127.0.0.1/tabelm.php" role="button" target="_blank">Deschide in fila noua</a>

<br>
<table  class="customers">
<thead>
<tr>
<th>ID</th>
<th>Data</th>
<th>Functie</th>
<th>Salariu</th>
<th>Comision</th>
<th>ID Angajat</th>

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
$sql = "SELECT * FROM contract_m";
$result = $connection->query($sql);
if (!$result) {
    die("Invalid query: ". $connection->error);
    }


    // read data of each row
while ($row = $result->fetch_assoc()) {
    echo "
    <tr>
    <td>$row[id_cm]</td>
    <td>$row[data]</td>
    <td>$row[functie]</td>
    <td>$row[salar_baza]</td>
    <td>$row[comision] </td>
     <td>$row[id_angajat] </td>
   
    </td>
    </tr>";}
    
?>
</thead>
</table>
</div>  </div>
          
        
          <div id="Contracte juridice" class="tabcontent">
          <div class= "container my-5">


<a class="btn btn-primary" href="http://127.0.0.1/tabelcj.php" role="button" target="_blank">Deschide in fila noua</a>

<br><br>
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
</div> </div>
          <div id="Rata" class="tabcontent">
          <div class= "container my-5">


<a class="btn btn-primary" href="http://127.0.0.1/tabelr.php" role="button" target="_blank">Deschide in fila noua</a>

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
</div>  </div>
          <script>
          function openCity(evt, cityName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
              tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
              tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.className += " active";
          }
          </script>
 <a class="btn btn-primary" href="index.html" role="button">Inapoi</a>
</body>
</html>