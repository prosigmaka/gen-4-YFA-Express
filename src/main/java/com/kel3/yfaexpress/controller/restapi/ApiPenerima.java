package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.PenerimaDto;
import com.kel3.yfaexpress.model.entity.Penerima;
import com.kel3.yfaexpress.repository.PenerimaRepository;
import com.kel3.yfaexpress.service.PenerimaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/penerima")
public class ApiPenerima {
    @Autowired
    private PenerimaRepository penerimaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PenerimaService penerimaService;

    @GetMapping()
    public List<PenerimaDto> getListPenerima() {
        List<Penerima> penerimaList = penerimaRepository.findAll();
        List<PenerimaDto> penerimaDtos =
                penerimaList.stream()
                        .map(penerima -> mapPenerimaToPenerimaDto(penerima))
                        .collect(Collectors.toList());
        return penerimaDtos;
    }

    private PenerimaDto mapPenerimaToPenerimaDto(Penerima penerima) {
        PenerimaDto penerimaDto = modelMapper.map(penerima, PenerimaDto.class);
        penerimaDto.setIdPenerima(penerima.getIdPenerima());
        penerimaDto.setNamaPenerima(penerima.getNamaPenerima());
        penerimaDto.setTelpPenerima(penerima.getTelpPenerima());
        penerimaDto.setKotaPenerima(penerima.getKotaPenerima());
        penerimaDto.setAlamatPenerima(penerima.getAlamatPenerima());
        penerimaDto.setKodePosPenerima(penerima.getKodePosPenerima());

        return penerimaDto;
    }

    @GetMapping("/{id}")
    public PenerimaDto getPenerima(@PathVariable Integer id) {
        Penerima penerima = penerimaRepository.findById(id).get();
        PenerimaDto penerimaDto = new PenerimaDto();
        modelMapper.map(penerima, penerimaDto);
        penerimaDto.setIdPenerima(penerima.getIdPenerima());
        penerimaDto.setNamaPenerima(penerima.getNamaPenerima());
        penerimaDto.setTelpPenerima(penerima.getTelpPenerima());
        penerimaDto.setKotaPenerima(penerima.getKotaPenerima());
        penerimaDto.setAlamatPenerima(penerima.getAlamatPenerima());
        penerimaDto.setKodePosPenerima(penerima.getKodePosPenerima());

        return penerimaDto;
    }

    @PostMapping
    public PenerimaDto editSavePenerima(@RequestBody PenerimaDto penerimaDto) {
       
        Penerima penerima = modelMapper.map(penerimaDto, Penerima.class);
        penerima.setIdPenerima(penerimaDto.getIdPenerima());
        penerima = penerimaService.savePenerimaMaterDetail(penerima);
        /*penerimaDtoDb sudah mengandung primary key penerima dan penerimadto*/
        PenerimaDto penerimaDtoDB = mapPenerimaToPenerimaDto(penerima);

        return penerimaDtoDB;
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer idPenerima) {
        penerimaRepository.deleteById(idPenerima);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTablePenerima() {
        penerimaRepository.deleteAll();
    }
}

