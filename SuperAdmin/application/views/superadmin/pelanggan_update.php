<div class="container-fluid">
	<div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Update Data Pelanggan
    </div>

    <?php foreach($tb_customer as $pl) : ?>

    	<form method="post" action="<?php echo base_url('superadmin/pelanggan/update_aksi')?>">

   			<input type="hidden" value="<?= $kr->id_customer ?>" name="id_customer"> 		

    		<div class="form-group">
    			<label>Nama Pelanggan</label>
    			<input type="hidden" name="nama_customer" value="<?php echo $pl->nama_customer?>">
    			<input type="text" name="nama_customer" class="form-control" value="<?php echo $pl->nama_customer?>">
    		</div>

    		<div class="form-group">
    			<label>Alamat</label>
    			<input type="hidden" name="alamat" value="<?php echo $pl->alamat?>">
    			<input type="text" name="alamat" class="form-control" value="<?php echo $pl->alamat?>">
    		</div>

    		<div class="form-group">
    			<label>Username</label>
    			<input type="text" name="username" class="form-control" value="<?php echo $pl->username?>">
    		</div>

    		<div class="form-group">
    			<label>Password</label>
    			<input type="text" name="password" class="form-control" value="<?php echo $pl->password?>">
    		</div>

    		<div class="form-group">
    			<label>Email</label>
    			<input type="text" name="email" class="form-control" value="<?php echo $pl->email?>">
    		</div>

            <div class="form-group">
                <label>No. Telepon</label>
                <input type="text" name="no_telephone" class="form-control" value="<?php echo $pl->no_telephone?>">
            </div>

    		<div class="form-group">
    			<label>Foto</label>
    			<input type="text" name="foto" class="form-control" value="<?php echo $pl->foto?>">
    		</div>

    		<button type="submit" class="btn btn-primary">Simpan</button>
    		
    	</form>

   <?php endforeach;?>
</div>