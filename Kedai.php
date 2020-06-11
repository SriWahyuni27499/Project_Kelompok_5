<?php
defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Kedai extends RestController {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();
    }

    public function index_get()
    {
        // Users from a data store e.g. database
        $this->load->model('Kedai_model','kedai');
        $kedai = $this->kedai->get_kedai();


        if ( $kedai )
        {
            // Set the response and exit
            $this->response( [
                'status' => true,
                'data' => $kedai
            ], 200 );
        }
        else
        {
            // Set the response and exit
            $this->response( [
                'status' => false,
                'message' => 'No users were found'
            ], 404 );
        }
    }
}