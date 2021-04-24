package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.entity.Barang;
import com.kel3.yfaexpress.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barang")
public class ApiBarang {
    @Autowired
    private BarangRepository barangRepository;

    @GetMapping
    public List<Barang> getAll () {
        return barangRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Barang> getById(@PathVariable Integer idBarang) {
        return barangRepository.findAllByIdBarang(idBarang);
    }

    @PostMapping
    public Barang save(@RequestBody Barang barang) {
        return barangRepository.save(barang);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer idBarang) {
        barangRepository.deleteById(idBarang);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableBarang() {
        barangRepository.deleteAll();
    }
}
