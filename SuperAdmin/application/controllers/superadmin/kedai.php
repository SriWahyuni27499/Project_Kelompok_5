<?php

class Kedai extends CI_Controller{

	public function index()
	{
		$data['kedai']  = $this->kedai_model->tampil_data()->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/kedai', $data);

	}

	public function input()
	{
		$data = array(
				'id_kedai'    => set_value('id_kedai'),
				'id_admin'    => set_value('id_admin'),
				'nama_kedai'  => set_value('nama_kedai'),
				'pemilik'     => set_value('pemilik'),
				'jam_buka'    => set_value('jam_buka'),
				'foto'        => set_value('foto'),
		);
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/kedai_form', $data);
	}

	public function input_aksi()
	{
		$this->_rules();

		if ($this->form_validation->run() == FALSE){
			$this->input();
		}else {
			$data = array(
					'id_admin'  => $this->input->post('id_admin', TRUE),
					'nama_kedai' => $this->input->post('nama_kedai', TRUE),
					'pemilik' => $this->input->post('pemilik', TRUE),
					'jam_buka' => $this->input->post('jam_buka', TRUE),
					'foto' => $this->input->post('foto', TRUE),
			);

			$this->kedai_model->input_data($data);
			$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Kedai Berhasil Ditambahkan!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
			redirect('superadmin/kedai');
		}
	}

	public function _rules()
	{
		$this->form_validation->set_rules('id_admin', 'id_admin', 'required', ['required' => 'Kode Admin wajib diisi!']);
		$this->form_validation->set_rules('nama_kedai', 'nama_kedai', 'required', ['required' => 'Nama Kedai wajib diisi!']);
		$this->form_validation->set_rules('pemilik', 'pemilik', 'required', ['required' => 'Nama Pemilik wajib diisi!']);
		$this->form_validation->set_rules('jam_buka', 'jam_buka', 'required', ['required' => 'Jam Buka wajib diisi!']);
		$this->form_validation->set_rules('foto', 'foto', 'required', ['required' => 'Foto wajib dimasukkan!']);
	}

	public function update($id)
	{
		$where = array('id_kedai' => $id);
		$data['tb_kedai'] =$this->kedai_model->edit_data($where,'tb_kedai')->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/kedai_update', $data);

	}

	public function update_aksi()
	{
		$id = $this->input->post('id_kedai');
		$id_kedai = $this->input->post('id_admin');
		$nama_kedai = $this->input->post('nama_kedai');
		$pemilik = $this->input->post('pemilik');
		$jam_buka = $this->input->post('jam_buka');
		$foto = $this->input->post('foto');

		$data = array(
				'id_admin' => $id_admin,
				'nama_kedai' => $nama_kedai,
				'pemilik' => $pemilik,
				'jam_buka' => $jam_buka,
				'foto' => $foto
		);

		$where = array(
			'id_kedai' => $id
		);

		$this->kedai_model->update_data($where, 'tb_kedai', $data);

		$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Kedai Berhasil Diupdate!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/kedai');
	}

	public function delete($id)
	{
		$where = array('id_kedai' => $id);
		$this->kedai_model->hapus_data($where,'tb_kedai');
		$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert">
					Data Kedai Berhasil Dihapus!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/kedai');

	}

}