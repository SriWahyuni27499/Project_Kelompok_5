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

				<?php if ($this->session->flashdata('success')):?>
				<div class="alert alert-success" role="alert">
					<?php echo $this->session->flashdata('success');?>
				</div>
				<?php endif;?>

				<!-- DataTables -->
				<div class="card mb-3">
					<div class="card-header">
						<a href="<?php echo site_url('superadmin/admin/add') ?>"><i class="fas fa-arrow-left"></i>Back</a>
					</div>
					<div class="card-body">

						<form action="<?php base_url('superadmin/admin/add')?>" method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label for="name">Nama Lengkap*</label>
								<input class="form-control <?php echo form_error('nama_lengkap')? 'is-invalid':''?>" type="text" name="nama_lengkap" placeholder="Nama Lengkap"/>
								<div class="invalid-feedback">
									<?php echo form_error('nama_lengkap')?>
								</div>
							</div>

							<div class="form-group">
								<label for="name">Username</label>
								<input class="form-control <?php echo form_error('username')? 'is-invalid':''?>" type="text" name="username" placeholder="Username"/>
								<div class="invalid-feedback">
									<?php echo form_error('username')?>
								</div>
							</div>

							<div class="form-group">
								<label for="name">Foto</label>
								<input class="form-control-file <?php echo form_error('image')? 'is-invalid':''?>" type="text" name="image"/>
								<div class="invalid-feedback">
									<?php echo form_error('image')?>
								</div>
							</div>

							<input class="btn btn-success" type="submit" name="btn" value="Save"/>
						</form>
					</div>

					<div class="card-footer small text-muted">
						* required field
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

	<?php $this->load->view("superadmin/_partials/scrolltop.php")?>
	<?php $this->load->view("superadmin/_partials/js.php")?>


</body>

</html>