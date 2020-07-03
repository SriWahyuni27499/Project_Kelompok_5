<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    include 'DatabaseConfig.php';

    $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

    $username = $_POST['username'];

    $password = $_POST['password'];

    $CheckSQL = "SELECT * FROM tb_customer WHERE username = '$username' && password = '$password'";

    $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));

    if(isset($check)){
        echo 'Login Berhasil';
    }else {
        echo 'Login Gagal';
    }
}
mysqli_close($con);
?>