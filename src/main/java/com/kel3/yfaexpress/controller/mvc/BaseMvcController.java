package com.kel3.yfaexpress.controller.mvc;

import java.security.Principal;
import java.util.Collection;

import com.kel3.yfaexpress.model.entity.Useraa;
import com.kel3.yfaexpress.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {

    @GetMapping("utama")
    public String utama() {
        return "user/utama";
    }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
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

    @GetMapping("login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String a = auth.getAuthorities().toString();
        if (a.equals("[ROLE_ANONYMOUS]")){
            return "login";
        }else{
        return "user/utama"; }}

    @GetMapping("/")
    public String home() { return "index"; }

}
