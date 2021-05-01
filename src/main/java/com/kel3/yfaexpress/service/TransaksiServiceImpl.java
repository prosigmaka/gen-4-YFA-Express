package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;
    @Autowired
    private PengirimRepository pengirimRepository;
    @Autowired
    private PenerimaRepository penerimaRepository;

    @Override
    public Transaksi saveTransaksiMaterDetail(Transaksi transaksi) {
        pengirimRepository.save(transaksi.getPengirim());
        penerimaRepository.save(transaksi.getPenerima());
        transaksi = transaksiRepository.save(transaksi);
        return transaksi;
    }
}



