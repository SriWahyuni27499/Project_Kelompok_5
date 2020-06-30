<?php
defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class DetilMakanan{
	public $id_trx;
	public $id_customer;
	public $total_harga;
	public $ongkir;
	public $koor_akhir_long;
	public $koor_akhir_lat;
	public $makanan;
	
	public function __get($prop){
		if(property_exists($this, $prop)){
			return $this->prop;
		}
	}

	public function __set($prop, $val){
		if(property_exists($this, $prop)){
			$this->prop = $val;
		}
		return $this;
	}
	
}

class Driver_trx_info extends RestController {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();
        $this->load->model('Driver_model');
    }

    public function index_post(){

		$driverinfo = $this->post('username');
		
		$id_driver = $this->Driver_model->get_id_driver($driverinfo);

		if(count($id_driver) <= 0){
			$this->response([
				"status" => false,
				"msg" => "Tidak ditemukan user"
			], 400);
			exit;
		}

		$data = $this->Driver_model->get_histori($id_driver[0]["id_driver"]);

		$penampung = [];

		foreach($data as $d){
			$dclass = new DetilMakanan;
			$dclass->id_trx = $d["id_trx"];
			$dclass->id_customer = $d["id_customer"];
			$dclass->total_harga = $d["total_harga"];
			$dclass->ongkir = $d["ongkir"];
			$dclass->makanan = [];
			
			$dataMakanan = $this->Driver_model->get_detail_trx($d["id_trx"]);
			foreach($dataMakanan as $mkan){
				$mkanan = $this->Driver_model->get_makanan($mkan["id_barang"]);
				foreach($mkanan as $makan){
					array_push($dclass->makanan, ["nama_makanan" => $makan["nama_barang"], "jumlah_beli" => $mkan["jumlah"]]);
				}
			}

			array_push($penampung, (array)$dclass);
		}

		


		if(count($data) <= 0){
			$this->response([
				"status" => false,
				"msg" => "Tidak ditemukan user"
			], 400);
		}else{
			$this->response([
				"status" => true,
				"data" => $penampung
			], 200);
		}
    }

}
