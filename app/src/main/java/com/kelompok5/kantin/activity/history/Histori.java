package com.kelompok5.kantin.activity.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.kelompok5.kantin.R;
import com.kelompok5.kantin.activity.beranda.Beranda;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


class Makanan{
    public String nama_makanan;
    public int harga_makanan;

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public int getHarga_makanan() {
        return harga_makanan;
    }

    public void setHarga_makanan(int harga_makanan) {
        this.harga_makanan = harga_makanan;
    }
}


class HistoriRecord{
    public String id_trx;
    public String id_customer;
    public Integer total_harga;
    public Integer ongkir;
    public List<Makanan> makanan;

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

    public Integer getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Integer total_harga) {
        this.total_harga = total_harga;
    }

    public Integer getOngkir() {
        return ongkir;
    }

    public void setOngkir(Integer ongkir) {
        this.ongkir = ongkir;
    }

    public List<Makanan> getMakanan() {
        return makanan;
    }

    public void setMakanan(List<Makanan> makanan) {
        this.makanan = makanan;
    }
}


public class Histori extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listItem;
    MainAdapter adapter;
    Button kembalii;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);

        expandableListView = findViewById(R.id.expandableListView);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        adapter= new MainAdapter(this,listGroup,listItem);
        expandableListView.setAdapter(adapter);
        initListData();


        kembalii= findViewById(R.id.buttonkeberanda);
        kembalii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Histori.this, Beranda.class);
                startActivity(back);

            }
        });

    }

    private void initListData() {

        Runnable runthis = new Runnable() {
            @Override
            public void run() {
                SharedPreferences username = getSharedPreferences("login", MODE_PRIVATE);
                String userString = username.getString("username", "");

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("username", userString);
                }catch (Exception e){

                }

                OkHttpClient client = new OkHttpClient();
                RequestBody req = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

                Request reqq = new Request.Builder().url("http://172.17.100.2/kantin/driver_trx_info").post(req).build();

                client.newCall(reqq).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        try{
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                listGroup.add("ID Transaksi : "+ jsonObject1.getString("id_trx"));
                                List<String> items = new ArrayList<>();

                                items.add("ID Customer : " + jsonObject1.getString("id_customer"));
                                items.add("Total Harga : " + jsonObject1.getString("total_harga"));
                                items.add("Ongkir : "+ jsonObject1.getString("ongkir"));

                                JSONArray makanan = jsonObject1.getJSONArray("makanan");

                                String builderMakanan = "";

                                for (int j = 0; j < makanan.length(); j++) {
                                    builderMakanan += "Nama Makanan : " + makanan.getJSONObject(j).getString("nama_makanan") + "\n";
                                    builderMakanan += "Jumlah Beli : " + makanan.getJSONObject(j).getString("jumlah_beli") + "\n";
                                    builderMakanan += "\n\n";
                                }

                                items.add(builderMakanan);

                                listItem.put("ID Transaksi : "+ jsonObject1.getString("id_trx"), items);
                                Histori.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        };

        Thread x = new Thread(runthis);
        x.start();


    }
}