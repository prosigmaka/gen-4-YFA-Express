package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Layanan.TABLELAYANAN)
public class Layanan {
    public static final String TABLELAYANAN = "t_layanan";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLELAYANAN)
    @SequenceGenerator(name = TABLELAYANAN, sequenceName = "t_layanan_seq")

    private Integer idLayanan;
    private String kategoriLayanan;
    private Integer biayaLayanan;

}