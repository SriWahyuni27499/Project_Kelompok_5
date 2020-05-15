<?php 
class kedaiModel extends CI_Model{
    public function get(){
        $this->load->database();
        return $this->db->get("tb_kedai")->result();
    }
}
?>