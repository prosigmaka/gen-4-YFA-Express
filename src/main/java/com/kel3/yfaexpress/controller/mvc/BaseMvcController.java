package com.kel3.yfaexpress.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
    @GetMapping("utama")
    public String utama() {
        return "user/utama";
    }

    @GetMapping("layanan")
    public String layanan() {
        return "user/layanan";
    }

    @GetMapping("dropoff")
    public String dropoff() {
        return "user/dropoff";
    }

    @GetMapping("kurir")
    public String kurir() {
        return "user/kurir";
    }
    


}