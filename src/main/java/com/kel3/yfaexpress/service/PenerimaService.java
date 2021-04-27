package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Penerima;

public interface PenerimaService {
    Penerima latTransactional();

    Penerima savePenerimaMaterDetail(Penerima penerima);
}