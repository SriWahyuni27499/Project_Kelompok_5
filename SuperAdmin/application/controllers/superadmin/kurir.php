<?php

class Kurir extends CI_Controller{

	public function index()
	{
		$data['kurir']  = $this->kurir_model->tampil_data()->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/kurir', $data);

	}

	public function input()
	{
		$data = array(
				'id_driver'    => set_value('id_driver'),
				'nama_driver'  => set_value('nama_driver'),
				'alamat'       => set_value('alamat'),
				'username'     => set_value('username'),
				'password'     => set_value('password'),
				'email'        => set_value('email'),
				'no_telephone' => set_value('no_telephone'),
				'foto'         => set_value('foto'),
		);
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/kurir_form', $data);
	}

	public function input_aksi()
	{
		$this->_rules();

		if ($this->form_validation->run() == FALSE){
			$this->input();
		}else {
			$data = array(
					'nama_driver'  => $this->input->post('nama_driver', TRUE),
					'alamat'       => $this->input->post('alamat', TRUE),
					'username'     => $this->input->post('username', TRUE),
					'email' 	   => $this->input->post('email', TRUE),
					'no_telephone' => $this->input->post('no_telephone', TRUE),
					'foto' 		   => $this->input->post('foto', TRUE),
			);

			$this->kurir_model->input_data($data);
			$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Kurir Berhasil Ditambahkan!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
			redirect('superadmin/kurir');
		}
	}

	public function _rules()
	{
		$this->form_validation->set_rules('nama_driver', 'nama_driver', 'required', ['required' => 'Nama Kurir wajib diisi!']);
		$this->form_validation->set_rules('alamat', 'alamat', 'required', ['required' => 'Alamat wajib diisi!']);
		$this->form_validation->set_rules('username', 'username', 'required', ['required' => 'Username wajib diisi!']);
		$this->form_validation->set_rules('password', 'password', 'required', ['required' => 'Password wajib diisi!']);
		$this->form_validation->set_rules('email', 'email', 'required', ['required' => 'Email wajib diisi!']);
		$this->form_validation->set_rules('no_telephone', 'no_telephone', 'required', ['required' => 'No. Telephon wajib diisi!']);
		$this->form_validation->set_rules('foto', 'foto', 'required', ['required' => 'Foto wajib dimasukkan!']);
	}

	public function update($id)
	{
		$where = array('id_driver' => $id);
		$data['tb_driver'] =$this->kurir_model->edit_data($where,'tb_driver')->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/kurir_update', $data);

	}

	public function update_aksi()
	{
		$id = $this->input->post('id_driver');
		$nama_driver = $this->input->post('nama_driver');
		$alamat = $this->input->post('alamat');
		$username = $this->input->post('username');
		$password = $this->input->post('password');
		$email = $this->input->post('email');
		$no_telephone = $this->input->post('no_telephone');
		$foto = $this->input->post('foto');

		$data = array(
				'nama_driver' => $nama_driver,
				'alamat' => $alamat,
				'username' => $username,
				'password' => $password,
				'email' => $email,
				'no_telephone' => $no_telephone,
				'foto' => $foto
		);

		$where = array(
			'id_driver' => $id
		);

		$this->kurir_model->update_data($where, 'tb_driver', $data);

		$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Kurir Berhasil Diupdate!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/kurir');
	}

	public function delete($id)
	{
		$where = array('id_driver' => $id);
		$this->kurir_model->hapus_data($where,'tb_driver');
		$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert">
					Data Kurir Berhasil Dihapus!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/kurir');

	}

}