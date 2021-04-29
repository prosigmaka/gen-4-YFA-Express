package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Kota.TABLE_NAME)
public class Kota {
    public static final String TABLE_NAME = "t_kota";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME , sequenceName = "t_kota_seq")
    private Integer idKota;
    private Integer estimasiBiaya;
    private String kota;



}
