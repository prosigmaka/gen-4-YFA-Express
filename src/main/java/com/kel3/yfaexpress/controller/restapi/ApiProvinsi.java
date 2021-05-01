package com.kel3.yfaexpress.controller.restapi;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kel3.yfaexpress.model.dto.ProvinsiDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/provinsi")
public class ApiProvinsi {

    private JSONObject rajaOngkir() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/province")
                .get()
                .addHeader("key", "76c89de2b5aa3ac812b3d0cc0f347ec8")
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("rajaongkir");
//            JSONArray jsonArray = jsonObject.getJSONArray("results");
        return jsonObject;
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
////                System.out.println(object.get("city_name"));
//                System.out.println(object);
//            }

    }

    @GetMapping
    public List<ProvinsiDto> getAll() throws IOException, JSONException {
        ApiProvinsi prov = new ApiProvinsi();
        String json = prov.rajaOngkir().getJSONArray("results").toString();
        ObjectMapper mapper = new ObjectMapper();
        List<ProvinsiDto> provinsiDtoList = mapper.readValue(json, new TypeReference<List<ProvinsiDto>>(){});
        System.out.println(provinsiDtoList);
        return provinsiDtoList;
    }

//    public static void main(String[] args) throws IOException, JSONException {
//        ApiProvinsi raja = new ApiProvinsi();
//        raja.getAll();
//    }
}
