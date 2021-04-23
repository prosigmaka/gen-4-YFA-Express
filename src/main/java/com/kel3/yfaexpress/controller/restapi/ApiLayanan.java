package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.entity.Layanan;
import com.kel3.yfaexpress.repository.LayananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/layanan")
public class ApiLayanan {
    @Autowired
    private LayananRepository layananRepository;

    @GetMapping
    public List<Layanan> getAll() {

        return layananRepository.findAll();
    }

    @PostMapping
    public Layanan save(@RequestBody Layanan layanan) {

        return layananRepository.save(layanan);
    }
}
