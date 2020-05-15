<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class AuthModel extends CI_Model {

	public function register($data)
	{
		$this->db->insert('tb_user',$data);
	}
}