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

    @GetMapping("user")
    public String user() {
        return "user/layanan";
    }

    @GetMapping("index")
    public String index() {
        return "user/index";
    }

}