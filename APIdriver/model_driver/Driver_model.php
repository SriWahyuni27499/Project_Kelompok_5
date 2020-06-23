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

}
