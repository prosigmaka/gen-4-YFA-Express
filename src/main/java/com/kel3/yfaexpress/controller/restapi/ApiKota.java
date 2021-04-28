package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.KotaDto;
import com.kel3.yfaexpress.model.entity.Kota;
import com.kel3.yfaexpress.repository.KotaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kota")
public class ApiKota {
    @Autowired
    private KotaRepository kotaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Kota> getAll() {
        return kotaRepository.findAll();
    }

    @GetMapping("/{idKota}")
    public KotaDto getById(@PathVariable Integer idKota) {
        Kota kota = kotaRepository.findById(idKota).get();
        KotaDto kotaDto = new KotaDto();
        modelMapper.map(kota, kotaDto);
        kotaDto.setIdKota(kota.getIdKota());
        return kotaDto;
    }

    @PostMapping
    public Kota save(@RequestBody Kota kota) {
        return kotaRepository.save(kota);
    }

    @DeleteMapping("/{idKota}")
    public void delete(@PathVariable Integer idKota) {
        kotaRepository.deleteById(idKota);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableKota() {
        kotaRepository.deleteAll();
    }
}
