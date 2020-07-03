<?php

class Pemilik_Kedai extends CI_Controller{

	public function __construct(){
		parent::__construct();
		$this->load->library('upload');
	}


	public function index()
	{
		$data['pemilik_kedai']  = $this->pemilik_kedai_model->tampil_data()->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/pemilik_kedai', $data);

	}

	public function input()
	{
		$data = array(
				'id_admin'     => set_value('id_admin'),
				'nama_admin'   => set_value('nama_admin'),
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
		$this->load->view('superadmin/pemilik_kedai_form', $data);
	}

	public function input_aksi()
	{
		$this->_rules();

		if ($this->form_validation->run() == FALSE){
			$this->input();
		}else {
			$data = array(
					'nama_admin'   => $this->input->post('nama_admin', TRUE),
					'alamat'       => $this->input->post('alamat', TRUE),
					'username'     => $this->input->post('username', TRUE),
					'email' 	   => $this->input->post('email', TRUE),
					'no_telephone' => $this->input->post('no_telephone', TRUE),
			);

					$foto = $_FILES['foto']['name'];

					if ($foto ==''){}else{
						echo $_SERVER['DOCUMENT_ROOT'];
						//$dir = str_replace("\\", "/", FCPATH);

						print_r(is_dir($_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/pemilik_kedai/'));
						print_r(is_writable($_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/pemilik_kedai/'));

						$config['upload_path']	 = $_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/pemilik_kedai/';
						$config['allowed_types'] = 'jpg|png|gif|jpeg';


						$this->upload->initialize($config);
						if(!$this->upload->do_upload('foto')){
							echo "Upload Gagal";
							print_r($this->upload->display_errors()); die();
						}else{
							$foto=$this->upload->data('file_name');
						}
					}


			$this->pemilik_kedai_model->input_data($data);
			$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Pemilik Kedai Berhasil Ditambahkan!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
			redirect('superadmin/pemilik_kedai');
		}
	}

	public function _rules()
	{
		$this->form_validation->set_rules('nama_admin', 'nama_admin', 'required', ['required' => 'Nama Pemilik Kedai wajib diisi!']);
		$this->form_validation->set_rules('alamat', 'alamat', 'required', ['required' => 'Alamat wajib diisi!']);
		$this->form_validation->set_rules('username', 'username', 'required', ['required' => 'Username wajib diisi!']);
		$this->form_validation->set_rules('password', 'password', 'required', ['required' => 'Password wajib diisi!']);
		$this->form_validation->set_rules('email', 'email', 'required', ['required' => 'Email wajib diisi!']);
		$this->form_validation->set_rules('no_telephone', 'no_telephone', 'required', ['required' => 'No. Telephon wajib diisi!']);
		// $this->form_validation->set_rules('foto', 'foto', 'required', ['required' => 'Foto wajib dimasukkan!']);
	}

	public function update($id)
	{
		$where = array('id_admin' => $id);
		$data['tb_admin'] =$this->pemilik_kedai_model->edit_data($where,'tb_admin')->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/pemilik_kedai_update', $data);

	}

	public function update_aksi()
	{
		$id = $this->input->post('id_admin');
		$nama_admin = $this->input->post('nama_admin');
		$alamat = $this->input->post('alamat');
		$username = $this->input->post('username');
		$password = $this->input->post('password');
		$email = $this->input->post('email');
		$no_telephone = $this->input->post('no_telephone');
		$foto = $this->input->post('foto');

		$data = array(
				'nama_admin' => $nama_admin,
				'alamat' => $alamat,
				'username' => $username,
				'password' => $password,
				'email' => $email,
				'no_telephone' => $no_telephone,
				'foto' => $foto
		);

		$where = array(
			'id_admin' => $id
		);

		$this->pemilik_kedai_model->update_data($where, 'tb_admin', $data);

		$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Pemilik Kedai Berhasil Diupdate!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/pemilik_kedai');
	}

	public function delete($id)
	{
		$where = array('id_admin' => $id);
		$this->pemilik_kedai_model->hapus_data($where,'tb_admin');
		$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert">
					Data Pemilik Kedai Berhasil Dihapus!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/pemilik_kedai');

	}

}