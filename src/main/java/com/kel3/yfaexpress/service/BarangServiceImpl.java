package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Barang;
import com.kel3.yfaexpress.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepository;
    @Autowired
    private BeratBarangRepository beratBarangRepository;
    @Autowired
    private LayananRepository layananRepository;
    @Autowired
    private KotaRepository kotaRepository;
    @Autowired
    private PengirimRepository pengirimRepository;
    @Autowired
    private PenerimaRepository penerimaRepository;

    @Override
    public Barang saveBarangMaterDetail(Barang barang) {
        pengirimRepository.save(barang.getPengirim());
        penerimaRepository.save(barang.getPenerima());
        barang = barangRepository.save(barang);
        barang.setLayanan(layananRepository.findById(barang.getIdLayanan()).get());
        barang.setBeratBarang(beratBarangRepository.findById(barang.getIdBeratBarang()).get());
        barang.setKota(kotaRepository.findById(barang.getIdKota()).get());
        return barang;
    }
}



