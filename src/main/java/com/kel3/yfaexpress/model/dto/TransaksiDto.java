package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class TransaksiDto {
    private Integer idTransaksi;

    private Integer idUser;
    private String UserName;
    private String emailUser;
    private String password;
    private String fullName;
    private Number telepon;
    private String email;

    private Integer idBarang;
//    copas isi dto Barang

    private String statusDelivery;
    private String statusPayment;

    private Integer idTotalBiaya;
//    copas isi Dto TotalBiaya

    private Integer idKurir;
    private String namaKurir;
    private Number noTelpKurir;
    private String fotoKurir;
}
