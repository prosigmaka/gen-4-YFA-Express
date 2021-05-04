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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
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
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
        return transaksiDto;
    }

    @GetMapping("/getFoto/{idTransaksi}")
    public byte[] getFoto(@PathVariable Integer idTransaksi) throws IOException {
        Transaksi transaksi = transaksiRepository.findById(idTransaksi).get();
        String userFolderPath = "D:/img/";
        String pathFile = userFolderPath + transaksi.getFotoPenerima();
        Path paths = Paths.get(pathFile);
        byte[] foto = Files.readAllBytes(paths);
        return foto;
    }

    @PostMapping(value="/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TransaksiDto editSave(@RequestPart(value = "transaksi", required = true) TransaksiDto transaksiDto,
                                 @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {

        String userFolderPath = "D:/img";
//                System.getProperty("user.dir").replace('\\', '/') +"/demo/src/main/resources/static/img";
        Path path = Paths.get(userFolderPath);
        Path filePath = path.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Upload file with size" + file.getSize() + " with name :  " + file.getOriginalFilename());

        Transaksi transaksi = modelMapper.map(transaksiDto, Transaksi.class);
        transaksi.setFotoPenerima(file.getOriginalFilename());

        transaksi = transaksiService.saveTransaksiMaterDetail(transaksi);
        TransaksiDto transaksiDtoDB = mapTransaksiToTransaksiDto(transaksi);
        return transaksiDtoDB;
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
