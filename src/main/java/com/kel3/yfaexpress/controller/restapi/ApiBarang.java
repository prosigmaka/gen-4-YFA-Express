package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.BarangDto;
import com.kel3.yfaexpress.model.dto.PenerimaDto;
import com.kel3.yfaexpress.model.dto.PengirimDto;
import com.kel3.yfaexpress.model.entity.Barang;
import com.kel3.yfaexpress.model.entity.Penerima;
import com.kel3.yfaexpress.model.entity.Pengirim;
import com.kel3.yfaexpress.repository.BarangRepository;
import com.kel3.yfaexpress.service.BarangService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/barang")
public class ApiBarang {
    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BarangService barangService;

    @GetMapping()
    public List<BarangDto> getListBarang() {
        List<Barang> barangList = barangRepository.findAll();
        List<BarangDto> barangDtos =
                barangList.stream()
                        .map(barang -> mapBarangToBarangDto(barang))
                        .collect(Collectors.toList());
        return barangDtos;
    }


    @GetMapping("/{id}")
    public BarangDto getBarang(@PathVariable Integer id) {
        Barang barang = barangRepository.findById(id).get();
        BarangDto barangDto = new BarangDto();

//        barangDto.setIdBarang(barang.getIdBarang());
//        barangDto.setNamaBarang(barang.getNamaBarang());
//        barangDto.setJumlahBarang(barang.getJumlahBarang());
        // sementara isi inii

        modelMapper.map(barang,barangDto);
        modelMapper.map(barang.getBeratBarang(),barangDto);
        modelMapper.map(barang.getLayanan(),barangDto);
        modelMapper.map(barang.getPengirim(),barangDto);
        modelMapper.map(barang.getPenerima(),barangDto);
        barangDto.setIdBarang(barang.getIdBarang());

        return barangDto;
    }

    @PostMapping
    public BarangDto save(@RequestBody BarangDto barangDto) {
        Pengirim pengirim = modelMapper.map(barangDto, Pengirim.class);
        Penerima penerima = modelMapper.map(barangDto, Penerima.class);
        Barang barang = modelMapper.map(barangDto, Barang.class);
        barang.setPenerima(penerima);
        barang.setPengirim(pengirim);
        barangService.saveBarangMaterDetail(barang);
        BarangDto barangDtoDB = mapBarangToBarangDto(barang);
        return barangDtoDB;
    }

    private BarangDto mapBarangToBarangDto(Barang barang) {
        BarangDto barangDto = modelMapper.map(barang, BarangDto.class);
        modelMapper.map(barang.getBeratBarang(),barangDto);
        modelMapper.map(barang.getLayanan(),barangDto);
        modelMapper.map(barang.getPengirim(),barangDto);
        modelMapper.map(barang.getPenerima(),barangDto);
        return barangDto;
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
