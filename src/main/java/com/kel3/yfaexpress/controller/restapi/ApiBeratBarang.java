package com.kel3.yfaexpress.controller.restapi;


import com.kel3.yfaexpress.model.dto.BeratBarangDto;
import com.kel3.yfaexpress.model.entity.BeratBarang;
import com.kel3.yfaexpress.repository.BeratBarangRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/berat-barang")
public class ApiBeratBarang {
    @Autowired
    private BeratBarangRepository beratBarangRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<BeratBarangDto> getAll () {
        List<BeratBarang> beratBarangList = beratBarangRepository.findAll();
        List<BeratBarangDto> beratBarangDto =
                beratBarangList.stream()
                        .map(bb -> entityToDto(bb))
                        .collect(Collectors.toList());
        return beratBarangDto;

    }

    private BeratBarangDto entityToDto(BeratBarang beratBarang) {
        BeratBarangDto bbDto = modelMapper.map(beratBarang, BeratBarangDto.class);
        bbDto.setIdBeratBarang(beratBarang.getIdBeratBarang());
        return bbDto;
    }

    @GetMapping("/{idBeratBarang}")
    public BeratBarangDto getById(@PathVariable Integer idBeratBarang) {
        BeratBarang beratBarang = beratBarangRepository.findById(idBeratBarang).get();
        BeratBarangDto beratBarangDto = new BeratBarangDto();
        modelMapper.map(beratBarang, beratBarangDto);
        beratBarangDto.setIdBeratBarang(beratBarang.getIdBeratBarang());
        return beratBarangDto;
    }

    @PostMapping
    public BeratBarang save(@RequestBody BeratBarang beratBarang) {
        return beratBarangRepository.save(beratBarang);
    }

    @DeleteMapping("/{idBeratBarang}")
    public void delete(@PathVariable Integer idBeratBarang) {
        beratBarangRepository.deleteById(idBeratBarang);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableBerat() {
        beratBarangRepository.deleteAll();
    }
}
