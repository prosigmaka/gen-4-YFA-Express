package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = BeratBarang.TABLE_NAME )
public class BeratBarang {
    public static final String TABLE_NAME = "t_berat_barang";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME , sequenceName = "t_berat_barang_seq")
    private Integer idBeratBarang;
    private String kategoriBeratBarang;
    private String biayaKategori;
}
