<div class="container-fluid">
	<div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Update Data Pemilik Kedai
    </div>

    <?php foreach($tb_admin as $pk) : ?>

    	<form method="post" action="<?php echo base_url('superadmin/pemilik_kedai/update_aksi')?>">

   			<input type="hidden" value="<?= $pk->id_admin ?>" name="id_admin"> 		

    		<div class="form-group">
    			<label>Nama Pemilik Kedai</label>
    			<input type="hidden" name="nama_admin" value="<?php echo $pk->nama_admin?>">
    			<input type="text" name="nama_admin" class="form-control" value="<?php echo $pk->nama_admin?>">
    		</div>

    		<div class="form-group">
    			<label>Alamat</label>
    			<input type="hidden" name="alamat" value="<?php echo $pk->alamat?>">
    			<input type="text" name="alamat" class="form-control" value="<?php echo $pk->alamat?>">
    		</div>

    		<div class="form-group">
    			<label>Username</label>
    			<input type="text" name="username" class="form-control" value="<?php echo $pk->username?>">
    		</div>

    		<div class="form-group">
    			<label>Password</label>
    			<input type="text" name="password" class="form-control" value="<?php echo $pk->password?>">
    		</div>

    		<div class="form-group">
    			<label>Email</label>
    			<input type="text" name="email" class="form-control" value="<?php echo $pk->email?>">
    		</div>

            <div class="form-group">
                <label>No. Telepon</label>
                <input type="text" name="no_telephone" class="form-control" value="<?php echo $pk->no_telephone?>">
            </div>

    		<div class="form-group">
    			<label>Foto</label>
    			<input type="text" name="foto" class="form-control" value="<?php echo $pk->foto?>">
    		</div>

    		<button type="submit" class="btn btn-primary">Simpan</button>
    		
    	</form>

   <?php endforeach;?>
</div>