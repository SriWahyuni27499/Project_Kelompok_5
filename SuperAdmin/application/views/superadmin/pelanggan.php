<div class="container-fluid">
    <!-- Page Heading -->
    <div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Pelanggan
    </div>

    <?php echo $this->session->flashdata('pesan')?>

    <?php echo anchor('superadmin/pelanggan/input',' <button class="btn btn-sm btn-primary mb-3"><i class="fas fa-plus fa-sm"></i>Tambah Data Pelanggan</button>')?>

    <table class="table table-bordered table-striped table-hover">
        <tr>
            <th>NO</th>
            <th>NAMA PELANGGAN</th>
            <th>ALAMAT</th>
            <th>USERNAME</th>
            <th>EMAIL</th>
            <th>NO. TELEPON</th>
            <th>FOTO</th>
            <th colspan="2">AKSI</th>
        </tr>

        <?php
        $no = 1;
        foreach ($pelanggan as $pl) :?>
        <tr>
            <td width="20px"><?php echo $no++ ?></td>
            <td><?php echo $pl->nama_customer ?></td>
            <td><?php echo $pl->alamat ?></td>
            <td><?php echo $pl->username ?></td>
            <td><?php echo $pl->email ?></td>
            <td><?php echo $pl->no_telephone ?></td>
            <td><?php echo $pl->foto ?></td>
            <td width="20px"><?php echo anchor('superadmin/pelanggan/update/'.$pl->id_customer,'<div class="btn btn-sm btn-primary"><i class="fa fa-edit"></i></div>')?></td>
            <td width="20px"><?php echo anchor('superadmin/pelanggan/delete/'.$pl->id_customer,'<div class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></div>')?></td>
        </tr>
        <?php endforeach;?>
    </table>
</div>