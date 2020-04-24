<!DOCTYPE html>
<html>
<head>
	<title>Daftar</title>
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
			<center><h2>DAFTAR</h2></center>
			<hr>
				<form class="form-horizontal" method="POST" action="<?php echo site_url('auth/proses_register');?>">
					<div class="form-group">
						<label>Nama Lengkap</label>
						<input type="text" class="form-control" name="nama_lengkap" placeholder="Nama Lengkap">
					</div>
					<div class="form-group">
						<label>Email</label>
						<input type="email" class="form-control" name="email" placeholder="Email">
					</div>
					<div class="form-group">
						<label>Kata Sandi</label>
						<input type="password" class="form-control" name="kata_sandi" placeholder="Kata Sandi">
					</div>
					<div class="form-group">
						<label>Konfirmasi Kata Sandi</label>
						<input type="password" class="form-control" name="konfirmasi_kata_sandi" placeholder="Konfirmasi Kata Sandi">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-succes">Daftar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>