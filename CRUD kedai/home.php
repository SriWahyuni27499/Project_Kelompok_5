<?php
class Home extends CI_Controller {
    public function index(){
        $this->load->model("kedaiModel");
        $data = array(
			"tb_kedai"=>$this->kedaiModel->get()
		);
		$this->load->view("kedaiView",$data);
	}
}
?>