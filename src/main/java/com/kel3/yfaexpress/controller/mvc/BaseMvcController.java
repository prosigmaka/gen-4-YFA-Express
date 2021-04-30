package com.kel3.yfaexpress.controller.mvc;

import com.kel3.yfaexpress.model.entity.Role;
import com.kel3.yfaexpress.model.entity.Useraa;
import com.kel3.yfaexpress.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping("layanan")
    public String layanan() {
        Role role = new Role();
        Useraa user = new Useraa();
        System.out.println(roleRepository.findById(1));
        return "user/layanan";
    }

    @GetMapping("utama")
    public String utama() {
        return "user/utama";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/")
    public String home() { return "index"; }


}
