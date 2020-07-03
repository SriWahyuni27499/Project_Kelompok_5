<div class="container-fluid">
<nav class="alert alert-success" >

        <center><i class="fas fa-biking"> </i>UPDATE DATA KURIR</center>

</nav>

    <?php foreach($tb_driver as $kr) : ?>

    	<?php echo form_open_multipart('superadmin/kurir/input_aksi');?>

   			<input type="hidden" value="<?= $kr->id_driver ?>" name="id_driver"> 		

    		<div class="form-group">
    			<label>Nama Kurir</label>
    			<input type="hidden" name="nama_driver" value="<?php echo $kr->nama_driver?>">
    			<input type="text" name="nama_driver" class="form-control" value="<?php echo $kr->nama_driver?>">
    		</div>

    		<div class="form-group">
    			<label>Alamat</label>
    			<input type="hidden" name="alamat" value="<?php echo $kr->alamat?>">
    			<input type="text" name="alamat" class="form-control" value="<?php echo $kr->alamat?>">
    		</div>

    		<div class="form-group">
    			<label>Username</label>
    			<input type="text" name="username" class="form-control" value="<?php echo $kr->username?>">
    		</div>

    		<div class="form-group">
    			<label>Password</label>
    			<input type="text" name="password" class="form-control" value="<?php echo $kr->password?>">
    		</div>

    		<div class="form-group">
    			<label>Email</label>
    			<input type="text" name="email" class="form-control" value="<?php echo $kr->email?>">
    		</div>

            <div class="form-group">
                <label>No. Telepon</label>
                <input type="text" name="no_telephone" class="form-control" value="<?php echo $kr->no_telephone?>">
            </div>

    		<div class="form-group">
    			<label>Upload Foto</label>
    			<input type="file" name="foto" class="form-control" value="<?php echo $kr->foto?>">
    		</div>

    		<button type="submit" class="btn btn-primary">Simpan</button>
    		
    	<?php echo form_close(); ?>

   <?php endforeach;?>
</div>