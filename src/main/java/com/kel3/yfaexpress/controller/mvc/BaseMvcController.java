package com.kel3.yfaexpress.controller.mvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return "index";
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


    @GetMapping("login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String a = auth.getAuthorities().toString();
        if (a.equals("[ROLE_ANONYMOUS]")){
            return "login";
        }else{
        return "user/utama"; }}

    @GetMapping("logout")
    public String logout() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String a = auth.getAuthorities().toString();
//        if (a.equals("[ROLE_ANONYMOUS]")){
            return "utama";}

    @GetMapping("/")
    public String home() { return "index"; }

}
