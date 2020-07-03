<div class="container-fluid">
<nav class="alert alert-success" >

        <center><i class="fas fa-utensils"> </i>UPDATE MENU</center>

</nav>

    <?php foreach($tb_barang as $mn) : ?>

    	<?php echo form_open_multipart('superadmin/menu/input_aksi');?>

   			<input type="hidden" value="<?= $mn->id_barang ?>" name="id_barang"> 		

    		<div class="form-group">
    			<label>Kode Jenis</label>
    			<input type="hidden" name="id_jenis" value="<?php echo $mn->id_jenis?>">
    			<input type="text" name="id_jenis" class="form-control" value="<?php echo $mn->id_jenis?>">
    		</div>

    		<div class="form-group">
    			<label>Kode Kedai</label>
    			<input type="hidden" name="id_kedai" value="<?php echo $mn->id_kedai?>">
    			<input type="text" name="id_kedai" class="form-control" value="<?php echo $mn->id_kedai?>">
    		</div>

    		<div class="form-group">
    			<label>Nama Menu</label>
    			<input type="text" name="nama_barang" class="form-control" value="<?php echo $mn->nama_barang?>">
    		</div>

    		<div class="form-group">
    			<label>Harga</label>
    			<input type="text" name="harga" class="form-control" value="<?php echo $mn->harga?>">
    		</div>

    		<div class="form-group">
    			<label>Stok</label>
    			<input type="text" name="stok" class="form-control" value="<?php echo $mn->stok?>">
    		</div>

    		<div class="form-group">
    			<label>Upload Foto</label>
    			<input type="file" name="foto" class="form-control" value="<?php echo $mn->foto?>">
    		</div>

    		<button type="submit" class="btn btn-primary">Simpan</button>
    		
    	<?php echo form_close(); ?>

   <?php endforeach;?>
</div>