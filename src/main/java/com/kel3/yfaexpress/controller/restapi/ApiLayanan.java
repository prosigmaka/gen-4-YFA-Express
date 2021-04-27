package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.entity.Layanan;
import com.kel3.yfaexpress.repository.LayananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

//    @GetMapping("/{id}")
//    public Layanan getById(@PathVariable ( value = "id") long id, Model model) {
////        return layananRepository.getByIdLayanan(id);
//    }

    @PostMapping
    public Layanan save(@RequestBody Layanan layanan) {
        return layananRepository.save(layanan);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        layananRepository.deleteById(id);
        return "";
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableLayanan() {
        layananRepository.deleteAll();
    }
}
