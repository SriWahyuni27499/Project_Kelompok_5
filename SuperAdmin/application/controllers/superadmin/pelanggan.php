<?php

class Pelanggan extends CI_Controller{

	public function __construct(){
		parent::__construct();
		$this->load->library('upload');
	}


	public function index()
	{
		$data['pelanggan']  = $this->pelanggan_model->tampil_data()->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/pelanggan', $data);

	}

	public function input()
	{
		$data = array(
				'id_customer'    => set_value('id_customer'),
				'nama_customer'  => set_value('nama_customer'),
				'alamat'       	 => set_value('alamat'),
				'username'     	 => set_value('username'),
				'password'     	 => set_value('password'),
				'email'       	 => set_value('email'),
				'no_telephone' 	 => set_value('no_telephone'),
				'foto'         	 => set_value('foto'),
		);
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/pelanggan_form', $data);
	}

	public function input_aksi()
	{
		$this->_rules();

		if ($this->form_validation->run() == FALSE){
			$this->input();
		}else {
			$data = array(
					'nama_customer'  => $this->input->post('nama_customer', TRUE),
					'alamat'       	 => $this->input->post('alamat', TRUE),
					'username'     	 => $this->input->post('username', TRUE),
					'email' 	   	 => $this->input->post('email', TRUE),
					'no_telephone' 	 => $this->input->post('no_telephone', TRUE),
			);

					$foto = $_FILES['foto']['name'];

					if ($foto ==''){}else{
						echo $_SERVER['DOCUMENT_ROOT'];
						//$dir = str_replace("\\", "/", FCPATH);

						print_r(is_dir($_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/pelanggan/'));
						print_r(is_writable($_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/pelanggan/'));

						$config['upload_path']	 = $_SERVER['DOCUMENT_ROOT'] . '/SuperAdmin/assets/foto/pelanggan/';
						$config['allowed_types'] = 'jpg|png|gif|jpeg';


						$this->upload->initialize($config);
						if(!$this->upload->do_upload('foto')){
							echo "Upload Gagal";
							print_r($this->upload->display_errors()); die();
						}else{
							$foto=$this->upload->data('file_name');
						}
					}

			$this->pelanggan_model->input_data($data);
			$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Pelanggan Berhasil Ditambahkan!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
			redirect('superadmin/pelanggan');
		}
	}

	public function _rules()
	{
		$this->form_validation->set_rules('nama_customer', 'nama_customer', 'required', ['required' => 'Nama Pelanggan wajib diisi!']);
		$this->form_validation->set_rules('alamat', 'alamat', 'required', ['required' => 'Alamat wajib diisi!']);
		$this->form_validation->set_rules('username', 'username', 'required', ['required' => 'Username wajib diisi!']);
		$this->form_validation->set_rules('password', 'password', 'required', ['required' => 'Password wajib diisi!']);
		$this->form_validation->set_rules('email', 'email', 'required', ['required' => 'Email wajib diisi!']);
		$this->form_validation->set_rules('no_telephone', 'no_telephone', 'required', ['required' => 'No. Telephon wajib diisi!']);
		// $this->form_validation->set_rules('foto', 'foto', 'required', ['required' => 'Foto wajib dimasukkan!']);
	}

	public function update($id)
	{
		$where = array('id_customer' => $id);
		$data['tb_customer'] =$this->pelanggan_model->edit_data($where,'tb_customer')->result();
		$this->load->view('templates_superadmin/header');
		$this->load->view('templates_superadmin/sidebar');
		$this->load->view('templates_superadmin/footer');
		$this->load->view('superadmin/pelanggan_update', $data);

	}

	public function update_aksi()
	{
		$id = $this->input->post('id_customer');
		$nama_driver = $this->input->post('nama_customer');
		$alamat = $this->input->post('alamat');
		$username = $this->input->post('username');
		$password = $this->input->post('password');
		$email = $this->input->post('email');
		$no_telephone = $this->input->post('no_telephone');
		$foto = $this->input->post('foto');

		$data = array(
				'nama_customer' => $nama_customer,
				'alamat' => $alamat,
				'username' => $username,
				'password' => $password,
				'email' => $email,
				'no_telephone' => $no_telephone,
				'foto' => $foto
		);

		$where = array(
			'id_customer' => $id
		);

		$this->pelanggan_model->update_data($where, 'tb_customer', $data);

		$this->session->set_flashdata('pesan','<div class="alert alert-success alert-dimissible fade show" role="alert">
					Data Pelanggan Berhasil Diupdate!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/pelanggan');
	}

	public function delete($id)
	{
		$where = array('id_customer' => $id);
		$this->pelanggan_model->hapus_data($where,'tb_customer');
		$this->session->set_flashdata('pesan','<div class="alert alert-danger alert-dimissible fade show" role="alert">
					Data Pelanggan Berhasil Dihapus!
					<button type="button" class="close" data-dismiss="alert aria-label="Close"><span
					aria-hidden="true">&times;</span>
					</button>
				</div>');
		redirect('superadmin/pelanggan');

	}

}