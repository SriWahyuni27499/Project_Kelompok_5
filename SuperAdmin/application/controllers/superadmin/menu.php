<?php

class Menu extends CI_Controller{

	public function __construct(){
		parent::__construct();
		$this->load->library('upload');
	}

	public function index()
	{
		$data['menu'] = $this->db->query("SELECT * FROM tb_barang br, tb_jenis js, tb_kedai kd WHERE br.id_jenis=js.id_jenis AND br.id_kedai=kd.id_kedai")->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/menu', $data);

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
		$this->load->view('superadmin/menu_form', $data);
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
			);

					$foto = $_FILES['foto']['name'];

					if ($foto ==''){}else{
						echo $_SERVER['DOCUMENT_ROOT'];
						//$dir = str_replace("\\", "/", FCPATH);

						print_r(is_dir($_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/menu/'));
						print_r(is_writable($_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/menu/'));

						$config['upload_path']	 = $_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/menu/';
						$config['allowed_types'] = 'jpg|png|gif|jpeg';


						$this->upload->initialize($config);
						if(!$this->upload->do_upload('foto')){
							echo "Upload Gagal";
							print_r($this->upload->display_errors()); die();
						}else{
							$foto=$this->upload->data('file_name');
						}
					}

			$this->menu_model->input_data($data);
			$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Menu Berhasil Ditambahkan!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
			redirect('superadmin/menu');
		}
	}

	public function _rules()
	{
		$this->form_validation->set_rules('id_jenis', 'id_jenis', 'required', ['required' => 'Kode Jenis wajib diisi!']);
		$this->form_validation->set_rules('id_kedai', 'id_kedai', 'required', ['required' => 'Kode Kedai wajib diisi!']);
		$this->form_validation->set_rules('nama_barang', 'nama_barang', 'required', ['required' => 'Nama Makanan wajib diisi!']);
		$this->form_validation->set_rules('harga', 'harga', 'required', ['required' => 'Harga wajib diisi!']);
		$this->form_validation->set_rules('stok', 'stok', 'required', ['required' => 'Stok wajib diisi!']);
		//$this->form_validation->set_rules('foto', 'foto', 'required', ['required' => 'Foto wajib dimasukkan!']);
	}

	public function update($id)
	{
		$where = array('id_barang' => $id);
		$data['tb_barang'] =$this->menu_model->edit_data($where,'tb_barang')->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/menu_update', $data);

	}

	public function update_aksi()
	{
		$id = $this->input->post('id_barang');
		$id_jenis = $this->input->post('id_jenis');
		$id_kedai = $this->input->post('id_kedai');
		$nama_barang = $this->input->post('nama_barang');
		$harga = $this->input->post('harga');
		$stok = $this->input->post('stok');
		$foto = $_FILES['foto'];
		if ($foto=''){}else{
			$config['upload_path']	 ='./assets/foto';
			$config['allowed_types'] ='jpg|png|gif|jpeg';

			$this->load->library('upload', $config);
			if(!$this->upload->do_upload('foto')){
				echo "Upload Gagal"; die();
			}else{
				$foto=$this->upload->data('file_name');
			}
		}

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

		$this->menu_model->update_data($where, 'tb_barang', $data);

		$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Menu Berhasil Diupdate!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/menu');
	}

	public function delete($id)
	{
		$where = array('id_barang' => $id);
		$this->menu_model->hapus_data($where,'tb_barang');
		$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert">
					Data Menu Berhasil Dihapus!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/menu');

	}

}