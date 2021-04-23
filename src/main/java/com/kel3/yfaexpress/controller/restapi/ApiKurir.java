package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.entity.Kurir;
import com.kel3.yfaexpress.repository.KurirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kurir")
public class ApiKurir {
    @Autowired
    private KurirRepository kurirRepository;

    @GetMapping
    public List<Kurir> getAll() {

        return kurirRepository.findAll();
    }

    @PostMapping
    public Kurir save(@RequestBody Kurir kurir) {

        return kurirRepository.save(kurir);
    }
}
