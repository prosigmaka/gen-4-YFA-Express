package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class TransaksiDto {
    private Integer idTransaksi;

    //dto user
    private Integer idUser;
    private String UserName;
    private String emailUser;
    private String password;
    private String fullName;
    private Number telepon;
    private String email;

    //dto Barang
    private Integer idBarang;
    private String namaBarang;
    private Integer jumlahBarang;

    private Integer idBeratBarang;
    private String kategoriBeratBarang;
    private String biayaKategori;

    private Integer idLayanan;
    private String kategoriLayanan;
    private Integer biayaLayanan;

    //private Integer idJarak;

    private Integer idPengirim;
    private String namaPengirim;
    private Number telpPengirim;
    private String kotaPengirim;
    private String alamatPengirim;
    private Number kodePosPengirim;

    private Integer idPenerima;
    private String namaPenerima;
    private Number telpPenerima;
    private String kotaPenerima;
    private String alamatPenerima;
    private Number kodePosPenerima;
    //end of dto barang

    private String statusDelivery;
    private String statusPayment;

    //dto total biaya
    private Integer idTotalBiaya;
//    copas isi Dto TotalBiaya
    //end of dto total biaya

    //dto kurir
    private Integer idKurir;
    private String namaKurir;
    private Number noTelpKurir;
    private String fotoKurir;
    //end of dto kurir
}
