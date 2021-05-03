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
        return "utama/index";
    }

    @GetMapping("cekresi")
    public String cekresi() {
        return "cekresi/index";
    }

    @GetMapping("layanan")
    public String layanan() {
        return "layanan/index";
    }

    @GetMapping("dropoff")
    public String dropoff() {
        return "dropoff/index";
    }

    @GetMapping("kurir")
    public String kurir() {
        return "kurir/index";
    }

    @GetMapping("faq")
    public String faq() {
        return "faq/index";
    }

    @GetMapping("profil")
    public String profil() {
        return "profilperusahaan/index";
    }

    @GetMapping("transaksiadmin")
    public String transaksiadmin() {
        return "transaksi/admin";
    }

    @GetMapping("transaksiuser")
    public String transaksiuser() {
        return "transaksi/user";
    }

    @GetMapping("login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String a = auth.getAuthorities().toString();
        if (a.equals("[ROLE_ANONYMOUS]")){
            return "utama/index";
        }else{
        return "utama/index"; }}

//    @GetMapping("logout")
//    public String logout() {
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        String a = auth.getAuthorities().toString();
////        if (a.equals("[ROLE_ANONYMOUS]")){
//            return "utama";}

    @GetMapping("/")
    public String home() { return "utama/index"; }

}
