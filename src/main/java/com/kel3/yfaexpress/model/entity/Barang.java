package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Barang.TABLE_BARANG)
@Data
public class Barang {
    public static final String TABLE_BARANG = "t_barang";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLE_BARANG)
    @SequenceGenerator(name = TABLE_BARANG, sequenceName = "t_barang_seq")
    private Integer idBarang;
    private String namaBarang;
    private Integer jumlahBarang;
    private String keteranganBarang;
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
    @JoinColumn (name = "id_pengirim")
    private Pengirim pengirim;

    @OneToOne
    @JoinColumn (name = "id_penerima")
    private Penerima penerima;

}