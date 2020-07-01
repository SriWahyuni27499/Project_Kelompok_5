<div class="container-fluid">
    <!-- Page Heading -->
    <div class="alert alert-success" role="alert">
        <i class="fas fa-utensils"> </i>Kurir
    </div>

    <?php echo $this->session->flashdata('pesan')?>

    <?php echo anchor('superadmin/kurir/input',' <button class="btn btn-sm btn-primary mb-3"><i class="fas fa-plus fa-sm"></i>Tambah Kurir</button>')?>

    <table class="table table-bordered table-striped table-hover">
        <tr>
            <th>NO</th>
            <th>NAMA KURIR</th>
            <th>ALAMAT</th>
            <th>USERNAME</th>
            <th>EMAIL</th>
            <th>NO. TELEPON</th>
            <th>FOTO</th>
            <th colspan="2">AKSI</th>
        </tr>

        <?php
        $no = 1;
        foreach ($kurir as $kr) :?>
        <tr>
            <td width="20px"><?php echo $no++ ?></td>
            <td><?php echo $kr->nama_driver ?></td>
            <td><?php echo $kr->alamat ?></td>
            <td><?php echo $kr->username ?></td>
            <td><?php echo $kr->email ?></td>
            <td><?php echo $kr->no_telephone ?></td>
            <td><?php echo $kr->foto ?></td>
            <td width="20px"><?php echo anchor('superadmin/kurir/update/'.$kr->id_driver,'<div class="btn btn-sm btn-primary"><i class="fa fa-edit"></i></div>')?></td>
            <td width="20px"><?php echo anchor('superadmin/kurir/delete/'.$kr->id_driver,'<div class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></div>')?></td>
        </tr>
        <?php endforeach;?>
    </table>
</div>