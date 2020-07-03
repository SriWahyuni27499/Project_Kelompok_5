<div class="container-fluid">
    <nav class="alert alert-success" >

        <center><i class="fas fa-money-check-alt"> </i>LAPORAN TRANSAKSI</center>

    </nav>
    <!-- Page Heading -->


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

    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            
            <tr>
                <th>NO</th>
                <th>KURIR</th>
                <th>PELANGGAN</th>
                <th>TANGGAL TRANSAKSI</th>
                <th>KETERANGAN</th>
                <th>TOTAL HARGA</th>
                <th>ONGKIR</th>
            </tr>

            <?php $no=1;
            foreach($laporan as $tr) : ?>

                <tr>
                    <td><?php echo $no++?></td>
                    <td><?php echo $tr->nama_driver?></td>
                    <td><?php echo $tr->nama_customer?></td>
                    <td><?php echo date('d/m/yy', strtotime($tr->tanggal_trx)); ?></td>
                    <td><?php echo $tr->keterangan?></td>
                    <td>Rp. <?php echo number_format($tr->total_harga,0,',','.')?></td>
                    <td>Rp. <?php echo number_format($tr->ongkir,0,',','.')?></td>
                </tr>
            <?php endforeach;?>

        </table>
    </div>

</div>

