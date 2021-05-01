package com.kel3.yfaexpress.controller.restapi;

import okhttp3.*;
import org.codehaus.groovy.tools.shell.IO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/cost")
public class ApiCost {

    public String rajaOngkirCost(String asal, String tujuan, String berat) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "origin=" + asal + "&destination=" + tujuan + "&weight=" + berat + "&courier=jne");
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/cost")
                .post(body)
                .addHeader("key", "76c89de2b5aa3ac812b3d0cc0f347ec8")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        //System.out.println(response.body().string());
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("rajaongkir");
        JSONArray jsonResults = jsonObject.getJSONArray("results");
        String jsonCosts = jsonResults.getJSONObject(0).toString();
        return jsonCosts;
        //return response.body().string();
    }

    @GetMapping
    public void getAll(String asal, String tujuan, String berat) throws IOException, JSONException {
        ApiCost cost = new ApiCost();

//        ObjectMapper mapper = new ObjectMapper();
//        List<CostDto> costDtoList = mapper.readValue(json, new TypeReference<List<KotaRajaDto>>() {
//        });
//        System.out.println(kotaDtoList);
//        return kotaDtoList;
        //       System.out.println(jsonCosts);
    }

    public static void main(String[] args) throws IOException, JSONException {
        ApiCost cost = new ApiCost();
        cost.rajaOngkirCost("22", "50", "1000");
    }


}
