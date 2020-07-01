<div class="container-fluid">
	<!-- Page Heading -->
    <div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Daftar Kedai
    </div>

    <?php echo $this->session->flashdata('pesan')?>

    <?php echo anchor('superadmin/kedai/input',' <button class="btn btn-sm btn-primary mb-3"><i class="fas fa-plus fa-sm"></i>Tambah Kedai</button>')?>

    <table class="table table-bordered table-striped table-hover">
    	<tr>
    		<th>NO</th>
    		<th>KODE ADMIN</th>
    		<th>NAMA KEDAI</th>
    		<th>PEMILIK</th>
    		<th>JAM BUKA</th>
    		<th>FOTO</th>
    		<th colspan="2">AKSI</th>
    	</tr>

    	<?php
    	$no = 1;
    	foreach ($kedai as $kd) :?>
    	<tr>
    		<td width="20px"><?php echo $no++ ?></td>
    		<td><?php echo $kd->id_admin ?></td>
    		<td><?php echo $kd->nama_kedai ?></td>
    		<td><?php echo $kd->pemilik ?></td>
    		<td><?php echo $kd->jam_buka ?></td>
    		<td><?php echo $kd->foto ?></td>
    		<td width="20px"><?php echo anchor('superadmin/kedai/update/'.$kd->id_kedai,'<div class="btn btn-sm btn-primary"><i class="fa fa-edit"></i></div>')?></td>
    		<td width="20px"><?php echo anchor('superadmin/kedai/delete/'.$kd->id_kedai,'<div class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></div>')?></td>
    	</tr>
    	<?php endforeach;?>
    </table>
</div>