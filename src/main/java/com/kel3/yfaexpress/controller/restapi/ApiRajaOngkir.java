package com.kel3.yfaexpress.controller.restapi;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api/city")
public class ApiRajaOngkir {

    @GetMapping
    public void rajaOngkir() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/city")
                .get()
//                .header("accept", "aplication/json")
                .addHeader("key", "76c89de2b5aa3ac812b3d0cc0f347ec8")
                .build();

//        ObjectMapper objectMapper = new ObjectMapper();
        Response response = client.newCall(request).execute();
//        Provinsi entity = objectMapper.readValue(response.body().string(), Provinsi.class);
//        Assert.notNull(entity);
        System.out.println(response.body().string());
//        String jsonData =
//        try {
//            JSONObject Jobject = new JSONObject(jsonData);
//            return Jobject;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
    }

    public static void main(String[] args) throws IOException{
        ApiRajaOngkir r = new ApiRajaOngkir();
        r.rajaOngkir();
    }
}
