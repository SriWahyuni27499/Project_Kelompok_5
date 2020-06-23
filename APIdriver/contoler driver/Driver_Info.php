<?php
defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Driver_Info extends RestController {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();
        $this->load->model('Driver_model','Driver');
    }

    public function index_post(){

             $username = $this->post('username');
		
		$data = $this->Driver->getDriverByUsername($username);

		if(count($data) <= 0){
			$this->response([
				"status" => false,
				"msg" => "Tidak ditemukan user"
			], 400);
		}else{
			$this->response([
				"status" => true,
				"data" => $data
			], 200);
		}
    }

}
