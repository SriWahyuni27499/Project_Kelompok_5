<div class="container-fluid">
	<div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Input Menu
    </div>
	
	<?php echo form_open_multipart('superadmin/menu/input_aksi');?>
		<div class="form-group">
			<label>Kode Jenis</label>
			<input type="text" name="id_jenis" placeholder="Masukkan Kode Jenis" class="form-control">
			<?php echo form_error('id_jenis', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Kode Kedai</label>
			<input type="text" name="id_kedai" placeholder="Masukkan Kode Kedai" class="form-control">
			<?php echo form_error('id_kedai', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Nama Menu</label>
			<input type="text" name="nama_barang" placeholder="Masukkan Nama Menu" class="form-control">
			<?php echo form_error('nama_barang', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Harga</label>
			<input type="text" name="harga" placeholder="Masukkan Harga" class="form-control">
			<?php echo form_error('harga', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Stok</label>
			<input type="text" name="stok" placeholder="Masukkan Stok " class="form-control">
			<?php echo form_error('stok', '<div class="text-danger small" ml-3>')?>
		</div>

		<div class="form-group">
			<label>Upload Foto</label>
			<input type="file" name="foto"  class="form-control">
			<?php echo form_error('foto', '<div class="text-danger small" ml-3>')?>
		</div>

		<button type="submit" class="btn btn-primary">Simpan</button>

	<?php echo form_close(); ?>

</div>