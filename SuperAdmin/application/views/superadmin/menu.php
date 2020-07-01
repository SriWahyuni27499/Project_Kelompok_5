<div class="container-fluid">
	<!-- Page Heading -->
    <div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Menu
    </div>

    <?php echo $this->session->flashdata('pesan')?>

    <?php echo anchor('superadmin/menu/input',' <button class="btn btn-sm btn-primary mb-3"><i class="fas fa-plus fa-sm"></i>Tambah Menu Makanan</button>')?>

    <table class="table table-bordered table-striped table-hover">
    	<tr>
    		<th>NO</th>
    		<th>KODE JENIS</th>
    		<th>KODE KEDAI</th>
    		<th>NAMA MENU</th>
    		<th>HARGA</th>
    		<th>STOK</th>
    		<th>FOTO</th>
    		<th colspan="2">AKSI</th>
    	</tr>

    	<?php
    	$no = 1;
    	foreach ($menu as $mn) :?>
    	<tr>
    		<td width="20px"><?php echo $no++ ?></td>
    		<td><?php echo $mn->id_jenis ?></td>
    		<td><?php echo $mn->id_kedai ?></td>
    		<td><?php echo $mn->nama_barang ?></td>
    		<td><?php echo $mn->harga ?></td>
    		<td><?php echo $mn->stok ?></td>
    		<td><?php echo $mn->foto ?></td>
    		<td width="20px"><?php echo anchor('superadmin/menu/update/'.$mn->id_barang,'<div class="btn btn-sm btn-primary"><i class="fa fa-edit"></i></div>')?></td>
    		<td width="20px"><?php echo anchor('superadmin/menu/delete/'.$mn->id_barang,'<div class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></div>')?></td>
    	</tr>
    	<?php endforeach;?>
    </table>
</div>