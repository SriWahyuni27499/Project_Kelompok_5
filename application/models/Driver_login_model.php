<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Driver_login_model extends CI_Model
{
   public function __construct()
    {
        parent::__construct();
    }

   public function LoginApi($username, $password)
    {
        $result = $this->db->query("SELECT
                                        username,
                                        password
                                    FROM
                                        tb_driver
                                    WHERE
                                        username = '$username'
                                    AND password = '$password'");
        return $result->result();
    }
}
