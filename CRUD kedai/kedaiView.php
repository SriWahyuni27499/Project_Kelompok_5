<h1>kedai</h1>
<h3><a href="index.php/home/tambah">+ Tambah kedai </a></h3>
<table border = "1" cellpadding = "5">
    <tr>
        <th>id_kedai</th>
        <th>pemilik</th>
        <th>jam_buka</th>
        <th>id_admin</th>
    </tr>
<?php
foreach ($tb_kedai as $row){
?>    
    <tr>
        <td><?php echo $row->id_kedai; ?></td>
        <td><?php echo $row->pemilik; ?></td>
        <td><?php echo $row->jam_buka; ?></td>
        <td><?php echo $row->id_admin; ?></td>
        <td>
            <a href = "<?php echo "index.php/home/detail/" . $row->id_kedai; ?>">Detail<a>
            <a href = "<?php echo "index.php/home/ubah/" . $row->id_kedai; ?>">Ubah<a>
            <a href = "<?php echo "index.php/home/hapus/" . $row->id_kedai; ?>">Hapus<a>
        </td>
    </tr>
    <?php
}
?>
</table>
