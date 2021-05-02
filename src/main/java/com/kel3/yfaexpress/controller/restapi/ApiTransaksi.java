package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.TransaksiDto;
import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.model.entity.Penerima;
import com.kel3.yfaexpress.model.entity.Pengirim;
import com.kel3.yfaexpress.repository.TransaksiRepository;
import com.kel3.yfaexpress.repository.UserRepository;
import com.kel3.yfaexpress.service.TransaksiService;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/transaksi")
public class ApiTransaksi {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransaksiService transaksiService;

    @GetMapping()
    public List<TransaksiDto> getListBarang() {
        List<Transaksi> transaksiList = transaksiRepository.findAll();
        List<TransaksiDto> transaksiDtos =
                transaksiList.stream()
                        .map(transaksi -> mapTransaksiToTransaksiDto(transaksi))
                        .collect(Collectors.toList());
        return transaksiDtos;
    }


    @GetMapping("/{id}")
    public TransaksiDto getBarang(@PathVariable Integer id) {
        Transaksi transaksi = transaksiRepository.findById(id).get();
        TransaksiDto transaksiDto = new TransaksiDto();
        modelMapper.map(transaksi, transaksiDto);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        transaksiDto.setIdTransaksi(transaksi.getIdTransaksi());

        return transaksiDto;
    }

    @PostMapping
    public TransaksiDto save(@RequestBody TransaksiDto transaksiDto) throws IOException, JSONException {
        Pengirim pengirim = modelMapper.map(transaksiDto, Pengirim.class);
        Penerima penerima = modelMapper.map(transaksiDto, Penerima.class);
        Transaksi transaksi = modelMapper.map(transaksiDto, Transaksi.class);
        transaksi.setPenerima(penerima);
        transaksi.setPengirim(pengirim);
        transaksi.setIdUser(userRepository.findByEmail(transaksiDto.getEmail()).getId());
        transaksiService.saveTransaksiMaterDetail(transaksi);
        TransaksiDto transaksiDtoDB = mapTransaksiToTransaksiDto(transaksi);
        return transaksiDtoDB;
    }

    private TransaksiDto mapTransaksiToTransaksiDto(Transaksi transaksi) {
        TransaksiDto transaksiDto = modelMapper.map(transaksi, TransaksiDto.class);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        return transaksiDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer idTransaksi) {
        transaksiRepository.deleteById(idTransaksi);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableTransaksi() {
        transaksiRepository.deleteAll();
    }

}
