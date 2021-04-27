package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.BeratBarangDto;
import com.kel3.yfaexpress.model.dto.LayananDto;
import com.kel3.yfaexpress.model.entity.BeratBarang;
import com.kel3.yfaexpress.model.entity.Layanan;
import com.kel3.yfaexpress.repository.LayananRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/layanan")
public class ApiLayanan {
    @Autowired
    private LayananRepository layananRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Layanan> getAll() {
        return layananRepository.findAll();
    }

    @GetMapping("/{idLayanan}")
    public LayananDto getById(@PathVariable Integer idLayanan) {
        Layanan layanan = layananRepository.findById(idLayanan).get();
        LayananDto layananDto = new LayananDto();
        modelMapper.map(layanan, layananDto);
        layananDto.setIdLayanan(layanan.getIdLayanan());
        return layananDto;
    }

    @PostMapping
    public Layanan save(@RequestBody Layanan layanan) {
        return layananRepository.save(layanan);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        layananRepository.deleteById(id);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableLayanan() {
        layananRepository.deleteAll();
    }
}
