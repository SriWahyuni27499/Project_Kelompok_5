<?php

/**
 * 
 */
class Laporan extends CI_Controller{

	public function index()
	{
		$dari = Date('Y-m-d', strtotime($this->input->post('dari')));
		$sampai = Date('Y-m-d', strtotime($this->input->post('sampai')));

		;

		$this->_rules();

		if($this->form_validation->run() == FALSE ){
			$this->load->view('templates_superadmin/header');
			$this->load->view('templates_superadmin/sidebar');
			$this->load->view('superadmin/filter_laporan');
			$this->load->view('templates_superadmin/footer');
		}else{
			$data['laporan'] = $this->db->query("SELECT * FROM tb_trx tr, tb_driver dr, tb_customer cs WHERE tr.id_driver=dr.id_driver AND tr.id_customer=cs.id_customer AND date(tanggal_trx) >= '$dari' AND date(tanggal_trx) <= '$sampai' ")->result();
			$this->load->view('templates_superadmin/header');
			$this->load->view('templates_superadmin/sidebar');
			$this->load->view('superadmin/tampilkan_laporan', $data);
			$this->load->view('templates_superadmin/footer');
		}

	}

	public function _rules()
	{
		$this->form_validation->set_rules('dari','Dari Tanggal','required');
		$this->form_validation->set_rules('sampai','Sampai Tanggal','required');
	}
}


?>