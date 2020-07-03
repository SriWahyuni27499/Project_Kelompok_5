<?php

require_once('DatabaseConfig.php');

    $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

    $sql = "SELECT * FROM tb_barang";
	
	$r = mysqli_query($con,$sql);

	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		array_push($result,array(
			"nama_barang"=>$row['nama_barang'],
			"harga"=>$row['harga']
		));
	}

	echo json_encode(array('result'=>$result));
	
    mysqli_close($con);
?>