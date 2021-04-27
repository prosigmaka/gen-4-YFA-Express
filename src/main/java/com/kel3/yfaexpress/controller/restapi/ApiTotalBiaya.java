package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.TotalBiayaDto;
import com.kel3.yfaexpress.model.dto.LayananDto;
import com.kel3.yfaexpress.model.entity.TotalBiaya;
import com.kel3.yfaexpress.model.entity.Layanan;
import com.kel3.yfaexpress.repository.BeratBarangRepository;
import com.kel3.yfaexpress.repository.LayananRepository;
import com.kel3.yfaexpress.repository.TotalBiayaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/totalbiaya")
public class ApiTotalBiaya {
    @Autowired
    private TotalBiayaRepository totalBiayaRepository;

    @Autowired
    private BeratBarangRepository beratBarangRepository;

    @Autowired
    private LayananRepository layananRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TotalBiayaDto> getListTotalBiaya() {
        List<TotalBiaya> totalBiayaList = totalBiayaRepository.findAll();
        List<TotalBiayaDto> totalBiayaDto =
                totalBiayaList.stream().map(totalBiaya -> mapTotalBiayaToTotalBiayaDto(totalBiaya)).collect(Collectors.toList());
        return totalBiayaDto;
    }

    private TotalBiayaDto mapTotalBiayaToTotalBiayaDto(TotalBiaya totalBiaya) {
        TotalBiayaDto totalBiayaDto = modelMapper.map(totalBiaya, TotalBiayaDto.class);
        totalBiayaDto.setIdTotalBiaya(totalBiaya.getIdTotal());
//        modelMapper.map(totalBiaya.getIdBeratBarang(), totalBiayaDto);
//        modelMapper.map(totalBiaya.getIdLayanan(), totalBiayaDto);
        return totalBiayaDto;
    }



}
