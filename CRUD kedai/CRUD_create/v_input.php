<html>
<head>
	<title>Membuat CRUD dengan CodeIgniter | MalasNgoding.com</title>
</head>
<body>
	<center>
		<h1>Membuat CRUD dengan CodeIgniter | MalasNgoding.com</h1>
		<h3>Tambah data baru</h3>
	</center>
	<form action="<?php echo base_url(). 'crud/tambahAksi'; ?>" method="post">
		<table style="margin:20px auto;">
			<tr>
				<td>pemilik</td>
				<td><input type="text" name="pemilik"></td>
			</tr>
			<tr>
				<td>jam buka</td>
				<td><input type="text" name="jam buka"></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Tambah"> <a href="../../">kembali<a></td>
                <td></td>
			</tr>
            
		</table>
	</form>	
</body>
</html>