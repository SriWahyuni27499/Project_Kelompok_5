<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Customer_Login extends RestController{

    public function __construct()
    {
        parent::__construct();
        $this->load->model('Customer_Login_Model');
    }

    public function index_get()
    {
        echo 'Customer Login ';
    }

    public function index_post()
    {
        $username = $this->input->post('username');
        $password = $this->input->post('password');
        $result = $this->Customer_Login_Model->LoginApi($username, $password);
        if(count($result) > 0){
			$this->response([
				"status" => true,
				"data" => "Login Sukses"
			], 200);
		}else{
			$this->response( [
                'status' => false,
                'message' => 'Login gagal'
            ], 400 );
		}
    }
}
