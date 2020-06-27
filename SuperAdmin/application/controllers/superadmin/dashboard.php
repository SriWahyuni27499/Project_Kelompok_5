<?php

class Dashboard extends CI_Controller{

	public function index()
	{
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/dashboard');

	}
}