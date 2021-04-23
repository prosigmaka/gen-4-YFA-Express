package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Penerima.TABLEPENERIMA)
@Data
public class Penerima {
    public static final String TABLEPENERIMA = "t_penerima";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLEPENERIMA)
    @SequenceGenerator(name = TABLEPENERIMA, sequenceName = "t_penerima_seq")

    private Integer idPenerima;

    @Column(name ="nama_penerima",nullable = false)
    private String namaPenerima;

    @Column(name ="no_telp_penerima",nullable = false)
    private Number noTelpPenerima;

    @Column(name ="alamatpenerima",nullable = false)
    private Number alamatPenerima;

    @Column(name ="kode_pos_penerima",nullable = false)
    private Number kodePosPenerima;

    @Column(name ="kota_penerima",nullable = false)
    private Number kotaPenerima;

//    Belum ada entity KOTA

//    @ManyToOne
//    @JoinColumn (name = "kota_penerima",insertable = false, updatable = false)
//    private Kota kota;
//
//    @Column( name = "kota_penerima",nullable = false)
////    private Integer idKota;

}
