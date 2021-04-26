package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.entity.Kota;
import com.kel3.yfaexpress.repository.KotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kota")
public class ApiKota {
    @Autowired
    private KotaRepository kotaRepository;

    @GetMapping
    public List<Kota> getAll () {
        return kotaRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Kota> getById(@PathVariable Integer idKota) {
        return kotaRepository.findAllByIdKota(idKota);
    }

    @PostMapping
    public Kota save(@RequestBody Kota kota) {
        return kotaRepository.save(kota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer idKota) {
        kotaRepository.deleteById(idKota);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableKota() {
        kotaRepository.deleteAll();
    }
}
