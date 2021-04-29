package com.kel3.yfaexpress.controller.mvc;

import com.kel3.yfaexpress.model.dto.UserDto;
import com.kel3.yfaexpress.model.dto.UserRegistrationDto;
import com.kel3.yfaexpress.model.entity.Roles;
import com.kel3.yfaexpress.model.entity.Useraa;
import com.kel3.yfaexpress.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/dropoff")
    public String dropoff() {
        return "user/dropoff";
    }

    @GetMapping("admin/kurir")
    public String kurir() {
        return "admin/kurir";
    }

    @GetMapping("/beratLayanan")
    public String beratBarang() {
        return "admin/berat-layanan";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/")
    public String home() { return "index"; }

}
