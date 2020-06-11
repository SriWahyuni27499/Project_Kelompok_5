<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Kedai_model extends CI_Model {

    public function get_kedai(){
        return $this->db->get('tb_kedai')->result_array();
        
    }

}