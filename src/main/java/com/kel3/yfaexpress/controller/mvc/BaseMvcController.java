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
        return "user/FE-layanan";
    }

    @GetMapping("AdminUserDropOff")
    public String dropoff() {
        return "user/dropoff";
    }

    @GetMapping("AdminKurir")
    public String kurir() {
        return "admin/kurir";
    }

    @GetMapping("AdminBeratLayanan")
    public String beratBarang() {
        return "admin/berat-layanan";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/")
    public String home() { return "index"; }

}
