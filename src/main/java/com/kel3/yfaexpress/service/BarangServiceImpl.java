package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Barang;
import com.kel3.yfaexpress.repository.BarangRepository;
import com.kel3.yfaexpress.repository.BeratBarangRepository;
import com.kel3.yfaexpress.repository.LayananRepository;
import com.kel3.yfaexpress.repository.PengirimRepository;
import com.kel3.yfaexpress.repository.PenerimaRepository;
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
    private PengirimRepository pengirimRepository;
    @Autowired
    private PenerimaRepository penerimaRepository;


    @Override
    public Barang latTransactional() { return null; }

    @Override
    public Barang saveBarangMaterDetail(Barang barang) {
        barang = barangRepository.save(barang);
        barang.setBeratBarang(beratBarangRepository.findById(barang.getIdBeratBarang()).get());
        barang.setLayanan(layananRepository.findById(barang.getIdLayanan()).get());
        barang.setPengirim(pengirimRepository.findById(barang.getIdPengirim()).get());
        barang.setPenerima(penerimaRepository.findById(barang.getIdPenerima()).get());
        return barang;
    }
}



