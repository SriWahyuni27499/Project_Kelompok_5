<div class="container-fluid">
	<div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Update Kedai
    </div>

    <?php foreach($tb_kedai as $kd) : ?>

    	<form method="post" action="<?php echo base_url('superadmin/kedai/update_aksi')?>">

   			<input type="hidden" value="<?= $kd->id_kedai ?>" name="id_kedai"> 		

    		<div class="form-group">
    			<label>Kode Admin</label>
    			<input type="hidden" name="id_admin" value="<?php echo $kd->id_admin?>">
    			<input type="text" name="id_admin" class="form-control" value="<?php echo $kd->id_admin?>">
    		</div>

    		<div class="form-group">
    			<label>Nama Kedai</label>
    			<input type="text" name="nama_kedai" class="form-control" value="<?php echo $kd->nama_kedai?>">
    		</div>

    		<div class="form-group">
    			<label>Pemilik</label>
    			<input type="text" name="pemilik" class="form-control" value="<?php echo $kd->pemilik?>">
    		</div>

    		<div class="form-group">
    			<label>Jam Buka</label>
    			<input type="text" name="jam_buka" class="form-control" value="<?php echo $kd->jam_buka?>">
    		</div>

    		<div class="form-group">
    			<label>Foto</label>
    			<input type="text" name="foto" class="form-control" value="<?php echo $kd->foto?>">
    		</div>

    		<button type="submit" class="btn btn-primary">Simpan</button>
    		
    	</form>

   <?php endforeach;?>
</div>