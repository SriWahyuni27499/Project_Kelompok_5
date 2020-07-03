<?php

require_once('DatabaseConfig.php');

    $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

    $sql = "SELECT * FROM tb_customer";
	
	$r = mysqli_query($con,$sql);

	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		array_push($result,array(
			"nama_customer"=>$row['nama_customer'],
            "alamat"=>$row['alamat'],
            "email"=>$row['email'],
            "no_telephone"=>$row['no_telephone']
		));
	}

	echo json_encode(array('result'=>$result));
	
    mysqli_close($con);
?>