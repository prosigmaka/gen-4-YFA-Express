package com.kel3.yfaexpress.controller.restapi;


import com.kel3.yfaexpress.model.entity.BeratBarang;
import com.kel3.yfaexpress.repository.BeratBarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/berat-barang")
public class ApiBeratBarang {
    @Autowired
    private BeratBarangRepository beratBarangRepository;

    @GetMapping
    public List<BeratBarang> getAll () {
        return beratBarangRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<BeratBarang> getById(@PathVariable Integer idBeratBarang) {
        return beratBarangRepository.findAllByIdBeratBarang(idBeratBarang);
    }

    @PostMapping
    public BeratBarang save(@RequestBody BeratBarang beratBarang) {
        return beratBarangRepository.save(beratBarang);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer idBeratBarang) {
        beratBarangRepository.deleteById(idBeratBarang);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTablePengirim() {
        beratBarangRepository.deleteAll();
    }
}
