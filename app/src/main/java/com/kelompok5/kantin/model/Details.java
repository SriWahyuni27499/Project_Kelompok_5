package com.kelompok5.kantin.model;

public class Details{
    public String id_trx, id_customer, keterangan, nama_barang, kedai, hargabar;
    public int totalHarga, ongkir;
    public float koorAwalLat, koorAwalLong, koorAkhirLat, koorAkhirLong;


    public String getId_trx() {
        return id_trx;
    }

    public void setId_trx(String id_trx) {
        this.id_trx = id_trx;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public float getKoorAwalLat() {
        return koorAwalLat;
    }

    public void setKoorAwalLat(float koorAwalLat) {
        this.koorAwalLat = koorAwalLat;
    }

    public float getKoorAwalLong() {
        return koorAwalLong;
    }

    public void setKoorAwalLong(float koorAwalLong) {
        this.koorAwalLong = koorAwalLong;
    }

    public float getKoorAkhirLat() {
        return koorAkhirLat;
    }

    public void setKoorAkhirLat(float koorAkhirLat) {
        this.koorAkhirLat = koorAkhirLat;
    }

    public float getKoorAkhirLong() {
        return koorAkhirLong;
    }

    public void setKoorAkhirLong(float koorAkhirLong) {
        this.koorAkhirLong = koorAkhirLong;
    }
}