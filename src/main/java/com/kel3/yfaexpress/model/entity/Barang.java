package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Barang.TABLE_TOTAL_BIAYA)
@Data
public class Barang {
    public static final String TABLE_TOTAL_BIAYA = "t_barang";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= TABLE_TOTAL_BIAYA)
    @SequenceGenerator(name = TABLE_TOTAL_BIAYA, sequenceName = "t_barang_seq")


    private Integer idBarang;
    private String namaBarang;
    private Integer jumlahBarang;

    //private Integer idJarak;

    @ManyToOne
    @JoinColumn (name = "id_beratbarang",insertable = false, updatable = false)
    private BeratBarang beratBarang;
    @Column( name = "id_beratbarang",nullable = false)
    private Integer idBeratBarang;

    @ManyToOne
    @JoinColumn (name = "id_layanan",insertable = false, updatable = false)
    private Layanan layanan;
    @Column(name = "id_layanan",nullable = false)
    private Integer idLayanan;

    @OneToOne
    @JoinColumn (name = "id_pengirim",insertable = false, updatable = false)
    private Pengirim pengirim;
    @Column(name = "id_pengirim",nullable = false)
    private Integer idPengirim;

    @OneToOne
    @JoinColumn (name = "id_penerima",insertable = false, updatable = false)
    private Penerima penerima;
    @Column(name = "id_penerima",nullable = false)
    private Integer idPenerima;

}
