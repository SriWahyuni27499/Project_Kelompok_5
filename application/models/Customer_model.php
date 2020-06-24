<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Customer_model extends CI_Model {

    public function get_Customer(){
        return $this->db->get('tb_cotumer')->result_array();
        
		}
		
		public function getCustomerByUsername($username){
			return $this->db->get_where('tb_customer', ["username" => $username])->result_array();
		}

    public function post_Customer($Customer){

        $this->db->insert('tb_customer', $Customer);
      
        return $this->db->affected_rows();
      
      }
      public function put_Customer($Customerm){

				//$this->db->update('tb_driver', $Driverm, array('username' => $Driverm['username']));

        $this->db->where('username', $Customerm['username'])->update('tb_customer', $Customerm);


        return $this->db->affected_rows();
        
      
      }  

}
