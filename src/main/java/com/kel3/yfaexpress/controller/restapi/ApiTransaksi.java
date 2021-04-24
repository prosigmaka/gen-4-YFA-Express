package com.kel3.yfaexpress.controller.restapi;


import com.kel3.yfaexpress.model.dto.TransaksiDto;
import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.repository.TransaksiRepository;
import com.kel3.yfaexpress.service.TransaksiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaksi")
public class ApiTransaksi {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransaksiService transaksiService;

    @GetMapping
    public List<TransaksiDto> getListTransaksi() {
        List<Transaksi> transaksiList = transaksiRepository.findAll();
        List<TransaksiDto> transaksiDto =
                transaksiList.stream()
                        .map(transaksi -> mapTransaksiToTransaksiDto(transaksi))
                        .collect(Collectors.toList());
        return transaksiDto;
    }

    private TransaksiDto mapTransaksiToTransaksiDto(Transaksi transaksi) {
        TransaksiDto transaksiDto = modelMapper.map(transaksi, TransaksiDto.class);
        modelMapper.map(transaksi.getUser(), transaksiDto);
        modelMapper.map(transaksi.getKurir(), transaksiDto);
        modelMapper.map(transaksi.getBarang(), transaksiDto);
//        modelMapper.map(transaksi.getTotalBiaya(), transaksiDto);

        return transaksiDto;
    }

    @GetMapping("/{id}")
    public TransaksiDto getTransaksi(@PathVariable Integer id) {
        Transaksi transaksi = transaksiRepository.findById(id).get();
        TransaksiDto transaksiDto = new TransaksiDto();
        modelMapper.map(transaksi, transaksiDto);
//        belum tau mau ngisi apa

        return transaksiDto;
    }


//    kurang post mapping




    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        transaksiRepository.deleteById(id);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTable() {
        transaksiRepository.deleteAll();
    }



}
