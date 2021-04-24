package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.repository.BarangRepository;
import com.kel3.yfaexpress.repository.KurirRepository;
import com.kel3.yfaexpress.repository.TransaksiRepository;
import com.kel3.yfaexpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class TransaksiServiceImpl implements TransaksiService{
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BarangRepository barangRepository;

//    @Autowired
//    private TotalBiayaRepository totalBiayaRepository;

    @Autowired
    private KurirRepository kurirRepository;

    @Override
    public Transaksi saveTransaksi(Transaksi transaksi) {
        transaksi = transaksiRepository.save(transaksi);
//        belum tau mau ngisi apa




        return transaksi;
    }

}
