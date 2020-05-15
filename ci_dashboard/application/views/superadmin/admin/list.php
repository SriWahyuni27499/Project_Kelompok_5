<!DOCTYPE html>
<html lang="en">

<head>
	<?php $this->load->view("superadmin/_partials/head.php") ?>
</head>

<body id="page-top">
	
	<?php $this->load->view("superadmin/_partials/navbar.php") ?>
	<div id="wrapper">

		<?php $this->load->view("superadmin/_partials/sidebar.php") ?>

		<div id="content-wrapper">
			
			<div class="container-fluid">

				<?php $this->load->view("superadmin/_partials/breadcrumb.php") ?>

				<!-- DataTables -->
				<div class="card mb-3">
					<div class="card-header">
						<a href="<?php echo site_url('superadmin/admin/add') ?>"><i class="fas fa-plus"></i> Add New</a>
					</div>
					<div class="card-body">

						<div class="table-resonsive">
							<table class="table table-hover" id="dataTable" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>Nama Lengkap</th>
										<th>Username</th>
										<th>Foto</th>
									</tr>
								</thead>
								<tbody>
									<?php foreach ($admin as $admin): ?>
									<tr>
										<td width="150">
											<?php echo $admin->nama_lengkap ?>
										</td>
										<td>
											<?php echo $admin->username ?>
										</td>
										<td>
											<img src="<?php echo base_url('upload/admin/'.$admin->image) ?>" width="64"/>
										</td>
										<td width="250">
											<a href="<?php echo site_url ('superadmin/admin/edit/'.$admin->id_admin)?>" class="btn btn-small"><i class="fas fa-edit"></i> Edit </a>
											<a onclick="deleteConfirm('<?php echo site_url('superadmin/admin/delete'.$admin->id_admin)?>')" href="#!" class="btn btn-small text-danger"><i class="fas fa-trash"></i> Hapus </a>
										</td>
										</tr>
										<?php endforeach; ?>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
			<?php $this->load->view ("superadmin/_partials/footer.php")?>
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- /#wrapper -->

	<?php $this->load->view("superadmin_partials/scrolltop.php")?>
	<?php $this->load->view("superadmin_partials/modal.php")?>
	<?php $this->load->view("superadmin_partials/js.php")?>


</body>

</html>