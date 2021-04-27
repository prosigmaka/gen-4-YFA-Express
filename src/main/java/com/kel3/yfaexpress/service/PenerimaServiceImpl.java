package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Penerima;
import com.kel3.yfaexpress.repository.PenerimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PenerimaServiceImpl implements PenerimaService {

    @Autowired
    private PenerimaRepository penerimaRepository;

    @Override
    public Penerima latTransactional() {
        return null;
    }

    @Override
    public Penerima savePenerimaMaterDetail(Penerima penerima) {
        penerima = penerimaRepository.save(penerima);

        return penerima;
    }
}


