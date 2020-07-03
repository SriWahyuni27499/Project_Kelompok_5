<div class="container-fluid">
<nav class="alert alert-success" >

        <center><i class="fas fa-user-friends"> </i>INPUT DATA PEMILIK KEDAI</center>

</nav>
	
	<?php echo form_open_multipart('superadmin/pemilik_kedai/input_aksi');?>
		<div class="form-group">
			<label>Nama Pemilik Kedai</label>
			<input type="text" name="nama_admin" placeholder="Masukkan Nama Pemilik Kedai" class="form-control">
			<?php echo form_error('nama_admin', '<div class="text-danger small" ml-3>')?>
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
			<label>Upload Foto</label>
			<input type="file" name="foto"  class="form-control">
			<?php echo form_error('foto', '<div class="text-danger small" ml-3>')?>
		</div>

		<button type="submit" class="btn btn-primary">Simpan</button>
	<?php echo form_close(); ?>

</div>