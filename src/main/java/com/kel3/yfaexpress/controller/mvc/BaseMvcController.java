package com.kel3.yfaexpress.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
    @GetMapping("layanan")
    public String layanan() {
        return "user/layanan";
    }

    @GetMapping("utama")
    public String utama() {
        return "user/utama";
    }


}