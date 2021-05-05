package com.kel3.yfaexpress.controller.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kel3.yfaexpress.model.dto.KotaDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/kotaRaja")
public class ApiKota {

    @GetMapping
    public List<KotaDto> rajaOngkirKota() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/city")
                .get()
                .addHeader("key", "b18a8f99e7591ac1acb94ecc9cd70568")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("rajaongkir");
        String json = jsonObject.getJSONArray("results").toString();
        ObjectMapper mapper = new ObjectMapper();
        List<KotaDto> kotaDtoList = mapper.readValue(json, new TypeReference<List<KotaDto>>(){});
        return kotaDtoList;
    }

    @GetMapping("/{idProvinsi}")
    public List<KotaDto> rajaOngkirKota(@PathVariable String idProvinsi) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/city?province=" + idProvinsi )
                .get()
                .addHeader("key", "b18a8f99e7591ac1acb94ecc9cd70568")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("rajaongkir");
        String json = jsonObject.getJSONArray("results").toString();
        ObjectMapper mapper = new ObjectMapper();
        List<KotaDto> kotaDtoList = mapper.readValue(json, new TypeReference<List<KotaDto>>(){});
        return kotaDtoList;
    }

}
