<?php
defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Customer_Info extends RestController {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();
        $this->load->model('Customer_model','Customer');
    }

    public function index_post(){

             $username = $this->post('username');
		
		$data = $this->Customer->getCustomerByUsername($username);

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
