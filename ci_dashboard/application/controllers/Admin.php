<?php defined ('BASEPATH') or exit ('No Dirext Script Access Allowed');
class Admin extends CI_Controller{
	public function __construct()
	{
		parent::__construct();
		$this->load->model("admin_model");
		$this->load->library('form_validation');
	}

	public function index()
	{
		$data["admin"] = $this->admin_model->getAll();
		$this->load->view("superadmin/admin/list", $data);
	}

	public function add()
	{
		$admin = $this->admin_model;
		$validation = $this->form_validation;
		$validation->set_rules($admin->rules());

		if ($validation->run()){
			$admin->save();
			$this->session->set_flashdata('success', 'Berhasil disimpan');
		}

		$this->load->view("superadmin/admin/new_form");
	}

	public function edit($id = null)
	{
		if (!isset($id)) redirect('superadmin/admin');

		$admin = $this->admin_model;
		$validation = $this->form_validation;
		$validation->set_rules($admin->rules());

		if ($validation->run()){
			$admin->update();
			$this->session->set_flashdata('success', 'Berhasil disimpan');
		}

		$data["admin"] = $admin->getById($id);
		if (!$data["admin"]) show_404();

		$this->load->view("superadmin/admin/edit_form", $data);
	}

	public function delete($id=null)
	{
		if (!isset($id)) show_404();

		if ($this->admin_model->delete($id)){
			redirect(site_url('superadmin/admin'));
		}
	}
}