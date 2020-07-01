<div class="container-fluid">
	<div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Input Kedai
    </div>
	
	<form method="post" action="<?php echo base_url ('superadmin/kedai/input_aksi')?>">
		<div class="form-group">
			<label>Kode Admin</label>
			<input type="text" name="id_admin" placeholder="Masukkan Kode Admin" class="form-control">
			<?php echo form_error('id_admin', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Nama Kedai</label>
			<input type="text" name="nama_kedai" placeholder="Masukkan Nama Kedai" class="form-control">
			<?php echo form_error('nama_kedai', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Pemilik</label>
			<input type="text" name="pemilik" placeholder="Masukkan Nama Pemilik" class="form-control">
			<?php echo form_error('pemilik', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Jam Buka</label>
			<input type="text" name="jam_buka" placeholder="Masukkan Jam Buka " class="form-control">
			<?php echo form_error('jam_buka', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Foto</label>
			<input type="text" name="foto" placeholder="Masukkan Foto" class="form-control">
			<?php echo form_error('foto', '<div class="text-danger small" ml-3>')?>
		</div>

		<button type="submit" class="btn btn-primary">Simpan</button>
	</form>

</div>