package com.kelompok5.kantin.activity.login;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class UploadFile extends AsyncTask<String, Void, Void> {
    private String Content;
    private String Error = null;
    String data = "";
    private BufferedReader reader;
    private String image, username;

    private Context context;

    public UploadFile(Context ctx, String Image, String uname) {
        this.context = ctx;
        this.image = Image;
        this.username = uname;

    }



    protected void onPreExecute() {
        try {

            data += "&" + URLEncoder.encode("image", "UTF-8") + "=" + "data:image/png;base64," + image;
            data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + username;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    protected Void doInBackground(String... urls) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            //make request
            writer.write(data);
            writer.flush();
            writer.close();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            Content = sb.toString();
//            OkHttpClient client = new OkHttpClient();
//            RequestBody req = new MultipartBody.Builder()
//                                .addFormDataPart("image", image)
//                                .addFormDataPart("username", username)
//                                .build();
//
//            Request request = new Request.Builder().url(urls[0]).post(req).header("Connection", "keep-alive").build();
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                    System.out.println(response.body().string());
//                }
//            });

        } catch (Exception ex) {
            Error = ex.getMessage();
        }
        return null;

    }


    protected void onPostExecute(Void unused) {
        // NOTE: You can call UI Element here.

//        Dialog pDialog;
//        pDialog.dismiss();
        try {

            if (Content != null) {
//                JSONObject jsonResponse = new JSONObject(Content);
                System.out.println(Content);
//                String status = jsonResponse.getString("status");
//                if ("200".equals(status)) {
//
//                    Toast.makeText(this.context, "File uploaded successfully", Toast.LENGTH_SHORT).show();
//
//                } else {
//
//                    Toast.makeText(this.context, "Something is wrong ! Please try again.", Toast.LENGTH_SHORT).show();
//                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
