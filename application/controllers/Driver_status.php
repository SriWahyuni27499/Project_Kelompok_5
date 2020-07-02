<?php
defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Driver_status extends RestController{


	public function index_postt(){
	$sdrivonoff=$this->Driver_model->Driver_statuss();
		
		if(count($sdrivonoff) <= 0){
			$this->response([
				"status" => false,
				"msg" => "Tidak ditemukan user"
			], 400);
			exit;
		}else{
			$this->response([
				"status" => true,
				"msg" => $sdrivonoff
			], 200);
			exit;
		}

}
if(count($data) <= 0){
	$this->response([
		"status" => false,
		"msg" => "Tidak ditemukan user"
	], 400);
}else{
	$this->response([
		"status" => true,
		"data" => $penampung
	], 200);
}

}
