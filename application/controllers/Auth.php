<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Auth extends CI_Controller {

	public function _construct()
	{
		parent::_construct();
		$this->load->model('AuthModel');
	}

	public function register()
	{
		$this->load->view('register');
	}

	public function proses_register()
	{
		$data = [
			'nama_lengkap' => $this->input->post('nama_lengkap'),
			'email' => $this->input->post('email'),
			'kata_sandi' => $this->input->post('kata_sandi')
		];

		$insert = $this->AuthModel->register($data);

		if ($insert){
			echo 'Berhasil Terdaftar';
		}else{
			echo 'Gagal Terdaftar';
		}
	}

	// public function index (){
	// 	$this->load->model("UserModel");
	// 	echo '<prev>';
	// 	print_r($this->UserModel->get());
	// 	echo '</prev>';
	// }


}
?>