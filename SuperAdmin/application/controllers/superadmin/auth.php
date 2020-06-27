<?php

class Auth extends CI_Controller{

	public function index()
	{
		$this->load->view('templates_superadmin/header');
		$this->load->view('superadmin/login');
		$this->load->view('templates_superadmin/footer');
	} 

	public function proses_login()
	{
		$this->form_validation->set_rules('username','username','required');
		$this->form_validation->set_rules('password','password','required');
		if ($this->form_validation->run() == FALSE) {
			$this->load->view('templates_superadmin/header');
			$this->load->view('superadmin/login');
			$this->load->view('templates_superadmin/footer');
		}else{
			$username = $this->input->post('username');
			$password = $this->input->post('password');


			$user = $username;
			$pass = MD5($password);

			$cek = $this->login_model->cek_login($user, $pass);

			if ($cek->num_rows() > 0){

				foreach ($cek->result() as $ck){
					$sess_data['username'] = $ck->username;
					$sess_data['email'] = $ck->email;

					$this->session->set_userdata($sess_data);
				}
				
			}else{
				$this->session->set_flashdata('pesan', 'Maaf Username dan Password Anda Salah');
				redirect('superadmin/auth');
			}
		}
	}
}