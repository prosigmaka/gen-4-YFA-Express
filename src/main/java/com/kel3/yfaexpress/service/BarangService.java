package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Barang;

public interface BarangService {
    Barang latTransactional();

    Barang saveBarangMaterDetail(Barang barang);
}
