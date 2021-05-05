package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private PengirimRepository pengirimRepository;

    @Autowired
    private PenerimaRepository penerimaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KurirRepository kurirRepository;

    public String generateResiNumber(){
        String noResi = "YFA";
        int code = (int) (Math.ceil(Math.random() * 1000000000) * 1);
        noResi += code;
        return noResi;
    }

    @Override
    public Transaksi saveTransaksiMaterDetail(Transaksi transaksi) {
        TransaksiServiceImpl resi = new TransaksiServiceImpl();
        pengirimRepository.save(transaksi.getPengirim());
        penerimaRepository.save(transaksi.getPenerima());
        transaksi = transaksiRepository.save(transaksi);
        transaksi.setUseraa(userRepository.findById(transaksi.getIdUser()).get());
        transaksi.setKurir(kurirRepository.findById(1).get());
        transaksi.setTanggalTransaksi(new Date());
        transaksi.setResi(resi.generateResiNumber());
        return transaksi;
    }
}



