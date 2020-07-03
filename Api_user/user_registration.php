<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    include 'DatabaseConfig.php';

    $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

    $nama_customer = $_POST['nama_customer'];

    $alamat = $_POST['alamat'];

    $username = $_POST['username'];

    $password = $_POST['password'];

    $email = $_POST['email'];

    $no_telephone = $_POST['no_telephone'];

    $CheckSQL = "SELECT * FROM tb_customer WHERE username = '$username'";

    $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));

    if(isset($check)){
        echo 'Akun sudah terdaftar, Coba Username lain.';
    }else {
        $Sql_Query = "INSERT INTO tb_customer (nama_customer,alamat,username,password,email,no_telephone) VALUES ('$nama_customer','$alamat','$username','$password','$email','$no_telephone')";
        if (mysqli_query($con,$Sql_Query)) {
        echo 'Berhasil Mendaftar';
        }else {
        echo 'Something went wrong';
        }
    }
}
mysqli_close($con);
?>