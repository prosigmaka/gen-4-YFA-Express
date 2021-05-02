package com.kel3.yfaexpress.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransaksiDto {
    private Integer idTransaksi;
    private String namaBarang;
    private Integer jumlahBarang;
    private String kategoriLayanan;
    private Integer ongkosKirim;
    private Integer beratBarang;
    private String statusDelivery;
    private Date tanggalTransaksi;
    private String resi;
    //email user yang melakukan order
    //kemudian simpan sebagai idUser
    private Integer idUser;
    private String UserName;
    private String emailUser;
    private String password;
    private String fullName;
    private Number telepon;
    private String email;

    private Integer idPengirim;
    private String namaPengirim;
    private String telpPengirim;
    private Integer province_id;
    private Integer cityPengirimId;
    private String provinceName;
    private String cityName;
    private String alamatPengirim;
    private String kodePosPengirim;

    private Integer idPenerima;
    private String namaPenerima;
    private String telpPenerima;
    private Integer provinceIdPenerima;
    private Integer cityPenerimaId;
    private String provinceNamePenerima;
    private String cityNamePenerima;
    private String alamatPenerima;
    private String kodePosPenerima;
}
