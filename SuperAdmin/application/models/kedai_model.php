<?php

class Kedai_model extends CI_Model{

	public function tampil_data()
	{
		return $this->db->get('tb_kedai');
	}

	public function input_data($data)
	{
		$this->db->insert('tb_kedai', $data);
	}

	public function edit_data($where, $table)
	{
		return $this->db->get_where($table,$where);
	}

	public function update_data($where, $table, $data)
	{
		$this->db->set($data);
		$this->db->where($where);
		$this->db->update($table);
	}

	public function hapus_data($where,$table)
	{
		$this->db->where($where);
		$this->db->delete($table);
	}
}