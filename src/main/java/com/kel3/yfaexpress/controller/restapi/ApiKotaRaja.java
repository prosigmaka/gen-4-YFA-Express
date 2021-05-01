package com.kel3.yfaexpress.controller.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kel3.yfaexpress.model.dto.KotaRajaDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/kotaRaja")
public class ApiKotaRaja {

        public JSONObject rajaOngkirKota() throws IOException, JSONException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.rajaongkir.com/starter/city")
                    .get()
                    .addHeader("key", "76c89de2b5aa3ac812b3d0cc0f347ec8")
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("rajaongkir");
            return jsonObject;
        }

        @GetMapping
        public List<KotaRajaDto> getAll() throws IOException, JSONException {
            ApiKotaRaja raja = new ApiKotaRaja();
            String json = raja.rajaOngkirKota().getJSONArray("results").toString();
            ObjectMapper mapper = new ObjectMapper();
            List<KotaRajaDto> kotaDtoList = mapper.readValue(json, new TypeReference<List<KotaRajaDto>>(){});
            System.out.println(kotaDtoList);
            return kotaDtoList;
        }

//        public static void main(String[] args) throws IOException, JSONException {
//            ApiKotaRaja raja = new ApiKotaRaja();
//            raja.getAll();
//        }

}
