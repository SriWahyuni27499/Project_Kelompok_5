<div class="container-fluid">
   <nav class="alert alert-success" >

        <center><i class="fas fa-user-friends"> </i>DATA PEMILIK KEDAI</center>

</nav>
 

    <?php echo $this->session->flashdata('pesan')?>

    <?php echo anchor('superadmin/pemilik_kedai/input',' <button class="btn btn-sm btn-primary mb-3"><i class="fas fa-plus fa-sm"></i>Tambah Pemilik Kedai</button>')?>

    <table class="table table-bordered table-striped table-hover">
        <tr>
            <th>NO</th>
            <th>NAMA PEMILIK KEDAI</th>
            <th>ALAMAT</th>
            <th>USERNAME</th>
            <th>EMAIL</th>
            <th>NO. TELEPON</th>
            <th colspan="2">AKSI</th>
        </tr>

        <?php
        $no = 1;
        foreach ($pemilik_kedai as $pk) :?>
        <tr>
            <td width="20px"><?php echo $no++ ?></td>
            <td><?php echo $pk->nama_admin ?></td>
            <td><?php echo $pk->alamat ?></td>
            <td><?php echo $pk->username ?></td>
            <td><?php echo $pk->email ?></td>
            <td><?php echo $pk->no_telephone ?></td>
            <td width="20px"><?php echo anchor('superadmin/pemilik_kedai/update/'.$pk->id_admin,'<div class="btn btn-sm btn-primary"><i class="fa fa-edit"></i></div>')?></td>
            <td width="20px"><?php echo anchor('superadmin/pemilik_kedai/delete/'.$pk->id_admin,'<div class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></div>')?></td>
        </tr>
        <?php endforeach;?>
    </table>
</div>