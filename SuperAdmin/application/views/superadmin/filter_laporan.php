<div class="container-fluid">
    <nav class="alert alert-success" >

        <center><i class="fas fa-money-check-alt"> </i>LAPORAN TRANSAKSI</center>

    </nav>

    <form method="post" action="<?php echo base_url('superadmin/laporan')?>">
    	<div class="form-group">
    		<label>Dari Tanggal</label>
    		<input type="date" name="dari" class="form-control">
    		<?php echo form_error('dari','<span class="text-small text-danger">','</span>')?>
    	</div>
    	
    	<div class="form-group">
    		<label>Sampai Tanggal</label>
    		<input type="date" name="sampai" class="form-control">
    		<?php echo form_error('sampai','<span class="text-small text-danger">','</span>')?>
    	</div>

    	<button type="submit" class="btn btn-sm btn-primary"><i class="fas fa-eye"></i>Tampilkan Data</button>
    </form>

</div>

