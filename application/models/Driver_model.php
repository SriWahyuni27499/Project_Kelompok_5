<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Driver_model extends CI_Model {

    public function get_Driver(){
        return $this->db->get('tb_driver')->result_array();
        
		}
		
		public function getDriverByUsername($username){
			return $this->db->get_where('tb_driver', ["username" => $username])->result_array();
		}

    public function post_Driver($Driver){

        $this->db->insert('tb_driver', $Driver);
      
        return $this->db->affected_rows();
      
      }
      public function put_Driver($Driverm){

				//$this->db->update('tb_driver', $Driverm, array('username' => $Driverm['username']));

        $this->db->where('username', $Driverm['username'])->update('tb_driver', $Driverm);


        return $this->db->affected_rows();
        
      
			} 
			
			public function get_id_driver($username){
				return $this->db->select("id_driver")->from("tb_driver")->where("username", $username)->get()->result_array();
			}

			public function get_detail_trx($id_trx){
				return $this->db->select("*")->from("tb_detail_trx")->where("id_trx", $id_trx)->get()->result_array();
			}
      public function get_histori($id_driver){
					return $this->db->select("*")->from("tb_trx")->where("tb_trx.id_driver", $id_driver)->get()->result_array();
      }
      
      public function get_makanan($id_makanan){
				return $this->db->select("nama_barang, harga")->from("tb_barang")->where("id_barang", $id_makanan)->get()->result_array();
      }

}
