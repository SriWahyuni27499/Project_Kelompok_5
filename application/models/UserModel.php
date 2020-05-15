<?php
class UserModel extends CI_Model {

	public function get (){
		$this->load->database();
		$this->db->select("*");
		$query = $this->db->get('tb_user');
		return $query->result();
	}

}
?>