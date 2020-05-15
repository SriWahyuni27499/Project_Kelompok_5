<?php defined ('BASEPATH') or exit ('No Dirext Script Access Allowed');
class Admin_model extends CI_Model{
	private $_table ="tb_admin";

	public $id_admin;
	public $nama_lengkap;
	public $username;
	public $foto="default.jpg";

	public function rules (){
		return[
				['field' => 'nama_lengkap',
				'label' => 'Nama Lengkap',
				'rules' => 'required'],

				['field' => 'username',
				'label' => 'Username',
				'rules' => 'required'],
		];
	}

	public function getAll(){
		return $this->db->get($this->_table)->result();
	}

	public function getById(){
		return $this->db->get_where($this->_table, ["id_admin" => $id])->row();
	}

	public function save()
	{
		$post = $this->input->post();
		$this->id_admin = uniqid();
		$this->nama_lengkap = $post ["nama_lengkap"];
		$this->username = $post ["username"];
		$this->db->insert($this->_table, $this);

	}

	public function update()
	{
		$post = $this->input->post();
		$this->id_admin = $post["id"];
		$this->nama_lengkap = $post ["nama_lengkap"];
		$this->username = $post ["username"];
		$this->db->insert($this->_table, $this, array('id_admin' => $post['id']));

	}

	public function delete()
	{
		return $this->db->delete($this->_table, array("id_admin" => $id));
	}

}