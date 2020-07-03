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
		$this->form_validation->set_rules('username','username','required', ['required' => 'Username wajib diisi!']);
		$this->form_validation->set_rules('password','password','required', ['required' => 'Password wajib diisi!']);

		if ($this->form_validation->run() == FALSE){
			$this->load->view('templates_superadmin/header');
			$this->load->view('superadmin/login');
			$this->load->view('templates_superadmin/footer');
		}else{
			$username = $this->input->post('username');
			$password = $this->input->post('password');

			$user = $username;
			$pass = MD5($password);

			$cek = $this->login_model->cek_login($user, $pass);

			if($cek->num_rows() > 0){

				foreach($cek->result() as $ck){
					$sess_data['username'] = $ck->username;

					$this->session->set_userdata($sess_data);
				}
				if($sess_data['username'] == 'superadmin'){
					redirect('superadmin/dashboard');
				}else{

					$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert"> Username atau Password Salah! <button type="button" class="close" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">&times;</span></button></div>');
					redirect('superadmin/auth');

				}

			}else{
				$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert"> Username atau Password Salah! <button type="button" class="close" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">&times;</span></button></div>');
				redirect('superadmin/auth');
			}
		}

	}

	public function logout()
	{
		$this->session->sess_destroy();
		redirect('superadmin/auth');
	}
}