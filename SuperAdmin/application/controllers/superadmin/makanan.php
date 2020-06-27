<?php

class Makanan extends CI_Controller{

	public function index()
	{
		$data['makanan']  = $this->makanan_model->tampil_data()->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/makanan', $data);

	}

	public function input()
	{
		$data = array(
				'id_barang'    => set_value('id_barang'),
				'id_jenis'     => set_value('id_jenis'),
				'id_kedai'     => set_value('id_kedai'),
				'nama_barang'  => set_value('nama_barang'),
				'harga'        => set_value('harga'),
				'stok'         => set_value('stok'),
				'foto'         => set_value('foto'),
		);
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/makanan_form', $data);
	}

	public function input_aksi()
	{
		$this->_rules();

		if ($this->form_validation->run() == FALSE){
			$this->input();
		}else {
			$data = array(
					'id_jenis' => $this->input->post('id_jenis', TRUE),
					'id_kedai'  => $this->input->post('id_kedai', TRUE),
					'nama_barang' => $this->input->post('nama_barang', TRUE),
					'harga' => $this->input->post('harga', TRUE),
					'stok' => $this->input->post('stok', TRUE),
					'foto' => $this->input->post('foto', TRUE),
			);

			$this->makanan_model->input_data($data);
			$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert">
					Data Menu Makanan Berhasil Ditambahkan!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
			redirect('superadmin/makanan');
		}
	}

	public function _rules()
	{
		$this->form_validation->set_rules('id_jenis', 'id_jenis', 'required', ['required' => 'Kode Jenis wajib diisi!']);
		$this->form_validation->set_rules('id_kedai', 'id_kedai', 'required', ['required' => 'Kode Kedai wajib diisi!']);
		$this->form_validation->set_rules('nama_barang', 'nama_barang', 'required', ['required' => 'Nama Makanan wajib diisi!']);
		$this->form_validation->set_rules('harga', 'harga', 'required', ['required' => 'Harga wajib diisi!']);
		$this->form_validation->set_rules('stok', 'stok', 'required', ['required' => 'Stok wajib diisi!']);
		$this->form_validation->set_rules('foto', 'foto', 'required', ['required' => 'Foto wajib dimasukkan!']);
	}

	public function update($id)
	{
		$where = array('id_barang' => $id);
		$data['tb_barang'] =$this->makanan_model->edit_data($where,'tb_barang')->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/makanan_update', $data);

	}

	public function update_aksi()
	{
		$id = $this->input->post('id_barang');
		$id_jenis = $this->input->post('id_jenis');
		$id_kedai = $this->input->post('id_kedai');
		$nama_barang = $this->input->post('nama_barang');
		$harga = $this->input->post('harga');
		$stok = $this->input->post('stok');
		$foto = $this->input->post('foto');

		$data = array(
				'id_jenis' => $id_jenis,
				'id_kedai' => $id_kedai,
				'nama_barang' => $nama_barang,
				'harga' => $harga,
				'stok' => $stok,
				'foto' => $foto
		);

		$where = array(
			'id_barang' => $id
		);

		$this->makanan_model->update_data($where, 'tb_barang', $data);

		$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Menu Makanan Berhasil Diupdate!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/makanan');
	}

	public function delete($id)
	{
		$where = array('id_barang' => $id);
		$this->makanan_model->hapus_data($where,'tb_barang');
		$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert">
					Data Menu Makanan Berhasil Dihapus!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/makanan');

	}

}