<div class="container-fluid">
	<div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Input Data Pelanggan
    </div>
	
	<form method="post" action="<?php echo base_url ('superadmin/pelanggan/input_aksi')?>">
		<div class="form-group">
			<label>Nama Pelanggan</label>
			<input type="text" name="nama_customer" placeholder="Masukkan Nama Pelanggan" class="form-control">
			<?php echo form_error('nama_customer', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Alamat</label>
			<input type="text" name="alamat" placeholder="Masukkan Alamat" class="form-control">
			<?php echo form_error('alamat', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Username</label>
			<input type="text" name="username" placeholder="Masukkan Username" class="form-control">
			<?php echo form_error('username', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Password</label>
			<input type="text" name="password" placeholder="Masukkan Password" class="form-control">
			<?php echo form_error('password', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Email</label>
			<input type="text" name="email" placeholder="Masukkan Email " class="form-control">
			<?php echo form_error('email', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>No. Telepon</label>
			<input type="text" name="no_telephone" placeholder="Masukkan No. Telepon" class="form-control">
			<?php echo form_error('no_telephone', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Foto</label>
			<input type="text" name="foto" placeholder="Masukkan Foto" class="form-control">
			<?php echo form_error('foto', '<div class="text-danger small" ml-3>')?>
		</div>

		<button type="submit" class="btn btn-primary">Simpan</button>
	</form>

</div>