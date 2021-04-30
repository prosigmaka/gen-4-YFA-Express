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
public class ApiRajaOngkir {

    public JSONObject rajaOngkir() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/province")
                .get()
                .addHeader("key", "76c89de2b5aa3ac812b3d0cc0f347ec8")
                .build();

//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("key","76c89de2b5aa3ac812b3d0cc0f347ec8");
//        conn.connect();
//
//        Scanner sc = new Scanner(url.openStream());
//        while(sc.hasNext())
//        {
//            inline+=sc.nextLine();
//        }
//        System.out.println(inline);
//        ObjectMapper objectMapper = new ObjectMapper();
        Response response = client.newCall(request).execute();
//        Provinsi entity = objectMapper.readValue(response.body().string(), Provinsi.class);
//        System.out.println(response.body().string());
        ResponseBody jsonData = response.body();
        try {
            JSONObject jsonObject = new JSONObject(jsonData.string()).getJSONObject("rajaongkir");
//            JSONArray jsonArray = jsonObject.getJSONArray("results");
            return jsonObject;
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
////                System.out.println(object.get("city_name"));
//                System.out.println(object);
//            }

//            System.out.println(jsonArray);
//            ArrayList<Object> listdata = new ArrayList<Object>();
//            if (jsonArray != null) {
//                //Iterating JSON array
//                for (int i=0;i<jsonArray.length();i++){
//                    //Adding each element of JSON array into ArrayList
//                    listdata.add(jsonArray.get(i));
//                }
//            }
            //Iterating ArrayList to print each element
//            for(int i=0; i<listdata.size(); i++) {
//                //Printing each element of ArrayList
//                System.out.println(listdata.get(i));
//            }

//            System.out.println(listdata);
//        return listdata;

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        URL url = new URL("https://api.rajaongkir.com/starter/city");
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.getHeaderField("76c89de2b5aa3ac812b3d0cc0f347ec8");
//        conn.connect();
//        int responsecode = conn.getResponseCode();
//        if (responsecode != 200) {
//            throw new RuntimeException("HttpResponseCode: " +responsecode);
//        } else {
//            System.out.println("berhasil");
//        }
        return null;
    }

    @GetMapping
    public List<ProvinsiDto> getAll() throws IOException, JSONException {
        ApiRajaOngkir raja = new ApiRajaOngkir();
        String json = raja.rajaOngkir().getJSONArray("results").toString();
        ObjectMapper mapper = new ObjectMapper();
        ProvinsiDto[] prov = mapper.readValue(json, ProvinsiDto[].class);
        List<ProvinsiDto> langList = mapper.readValue(json, new TypeReference<List<ProvinsiDto>>(){});
        System.out.println(langList);
        return langList;
    }

    public static void main(String[] args) throws IOException, JSONException {
        ApiRajaOngkir raja = new ApiRajaOngkir();
        raja.getAll();
    }
}
