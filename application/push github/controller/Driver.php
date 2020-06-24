<?php
defined('BASEPATH') OR exit('No direct script access allowed');

use chriskacerguis\RestServer\RestController;

class Driver extends RestController {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();
        $this->load->model('Driver_model','Driver');
    }

    public function index_get()
    {
        // Users from a data store e.g. database
        $Driver = $this->Driver->get_Driver();


        if ( $Driver )
        {
            // Set the response and exit
            $this->response( [
                'status' => true,
                'data' => $Driver
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
    public function index_post(){

        $Driver = [
             "id_driver" => '',
             "nama_driver" => $this->post('nama_driver'),
             "alamat" => $this->post('alamat'),
             "username" => $this->post('username'),
             "password" => $this->post('password'),
             "email" => $this->post('email'),
             "no_telephone"  => $this->post('no_telephone'),
             "foto"  => $this->post('foto')
        ];

        if( $this->Driver->post_Driver($Driver) > 0 ){
            $this->response( [
                'status' => true,
                'message' => 'sukses'
            ], 201 );
        }else{

              $this->response( [
                'status' => false,
                'message' => 'No users were found'
            ], 400 );

        }
    }
    public function index_put() {
        $Driverc = [
            "id_driver" => $this->put('id_driver'),
            "nama_driver" => $this->put('nama_driver'),
            "alamat" => $this->put('alamat'),
            "username" => $this->put('username'),
            "password" => $this->put('password'),
            "email" => $this->put('email'),
            "no_telephone"  => $this->put('no_telephone'),
            "foto"  => $this->put('foto')
       ];

       if( $this->Driver->put_Driver($Driverc) > 0 ){
        $this->response( [
            'status' => true,
            'message' => 'sukses'
        ], 201 );
    }else{

          $this->response( [
            'status' => false,
            'message' => 'No users were found'
        ], 400 );

    }

        // $id_driver = $this->Driver->put_Driver();
        // $Driver = $this->Driver->put_Driver();

        // $this->db->where('id_driver', $id_driver);
        // $update = $this->db->update('tb_driver', $Driver);

        // if ($update) {
        //      $this->response( [
        //         'status' => true,
        //         'message' => 'mantull'
            
        //     ], 201 );
        // } else {
        //     $this->response( [
        //         'status' => false,
        //         'message' => 'No users were found'
        //     ], 400 );
        // }
    }
}