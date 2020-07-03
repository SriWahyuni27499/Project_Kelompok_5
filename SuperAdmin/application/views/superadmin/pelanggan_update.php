<div class="container-fluid">
    <nav class="alert alert-success" >

        <center><i class="fas fa-users"> </i>UPDATE DATA PELANGGAN</center>

</nav>

    <?php foreach($tb_customer as $pl) : ?>

        <?php echo form_open_multipart('superadmin/pelanggan/input_aksi');?>

   			<input type="hidden" value="<?= $pl->id_customer ?>" name="id_customer"> 		

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
                <label>Upload Foto</label>
                <input type="file" name="foto" class="form-control" value="<?php echo $pl->foto?>">
            </div>

    		<button type="submit" class="btn btn-primary">Simpan</button>
    		
    	<?php echo form_close(); ?>

   <?php endforeach;?>
</div>