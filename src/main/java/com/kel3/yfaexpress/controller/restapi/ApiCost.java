package com.kel3.yfaexpress.controller.restapi;

import okhttp3.*;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/cost")
public class ApiCost {

    public void rajaOngkirCost() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        String asal = "20";
        String tujuan = "30";
        String berat = "2000";


        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "origin=" + asal + "&destination=" + tujuan + "&weight=" + berat +"&courier=jne");
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/cost")
                .post(body)
                .addHeader("key", "76c89de2b5aa3ac812b3d0cc0f347ec8")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public static void main(String[] args) throws IOException, JSONException {
        ApiCost cost = new ApiCost();
        cost.rajaOngkirCost();
    }
}
