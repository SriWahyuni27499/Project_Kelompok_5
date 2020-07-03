<div class="container-fluid">
<nav class="alert alert-success" >

        <center><i class="fas fa-utensils"> </i>MENU</center>

</nav>

    <?php echo $this->session->flashdata('pesan')?>

    <?php echo anchor('superadmin/menu/input',' <button class="btn btn-sm btn-primary mb-3"><i class="fas fa-plus fa-sm"></i>Tambah Menu Makanan</button>')?>

    <table class="table table-bordered table-striped table-hover">
    	<tr>
    		<th>NO</th>
    		<th>NAMA JENIS</th>
    		<th>NAMA KEDAI</th>
    		<th>NAMA MENU</th>
    		<th>HARGA</th>
    		<th>STOK</th>
    		<th colspan="2">AKSI</th>
    	</tr>

    	<?php
    	$no = 1;
    	foreach ($menu as $mn) :?>
    	<tr>
    		<td width="20px"><?php echo $no++ ?></td>
    		<td><?php echo $mn->jenis ?></td>
    		<td><?php echo $mn->nama_kedai ?></td>
    		<td><?php echo $mn->nama_barang ?></td>
    		<td><?php echo $mn->harga ?></td>
    		<td><?php echo $mn->stok ?></td>
    		<td width="20px"><?php echo anchor('superadmin/menu/update/'.$mn->id_barang,'<div class="btn btn-sm btn-primary"><i class="fa fa-edit"></i></div>')?></td>
    		<td width="20px"><?php echo anchor('superadmin/menu/delete/'.$mn->id_barang,'<div class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></div>')?></td>
    	</tr>
    	<?php endforeach;?>
    </table>
</div>