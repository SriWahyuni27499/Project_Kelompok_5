<div class="container-fluid">
	<div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Update Menu Makanan
    </div>

    <?php foreach($tb_barang as $mkn) : ?>

    	<form method="post" action="<?php echo base_url('superadmin/makanan/update_aksi')?>">

   			<input type="hidden" value="<?= $mkn->id_barang ?>" name="id_barang"> 		

    		<div class="form-group">
    			<label>Kode Jenis</label>
    			<input type="hidden" name="id_jenis" value="<?php echo $mkn->id_jenis?>">
    			<input type="text" name="id_jenis" class="form-control" value="<?php echo $mkn->id_jenis?>">
    		</div>

    		<div class="form-group">
    			<label>Kode Kedai</label>
    			<input type="hidden" name="id_kedai" value="<?php echo $mkn->id_kedai?>">
    			<input type="text" name="id_kedai" class="form-control" value="<?php echo $mkn->id_kedai?>">
    		</div>

    		<div class="form-group">
    			<label>Nama Makanan</label>
    			<input type="text" name="nama_barang" class="form-control" value="<?php echo $mkn->nama_barang?>">
    		</div>

    		<div class="form-group">
    			<label>Harga</label>
    			<input type="text" name="harga" class="form-control" value="<?php echo $mkn->harga?>">
    		</div>

    		<div class="form-group">
    			<label>Stok</label>
    			<input type="text" name="stok" class="form-control" value="<?php echo $mkn->stok?>">
    		</div>

    		<div class="form-group">
    			<label>Foto</label>
    			<input type="text" name="foto" class="form-control" value="<?php echo $mkn->foto?>">
    		</div>

    		<button type="submit" class="btn btn-primary">Simpan</button>
    		
    	</form>

   <?php endforeach;?>
</div>