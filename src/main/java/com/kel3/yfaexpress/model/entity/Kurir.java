package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Kurir.TABLEKURIR)
public class Kurir {
        public static final String TABLEKURIR = "t_kurir";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLEKURIR)
    @SequenceGenerator(name = TABLEKURIR, sequenceName = "t_kurir_seq")

    private Integer idKurir;
    private String namaKurir;
    private Number noTelpKurir;
    @Column(name = "url")
    private String file;

//      Belum ada entity Kota
//    @OneToOne
//    @JoinColumn (name = "id_kota",insertable = false, updatable = false)
//    private Kota kota;
//
//    @Column( name = "id_kota",nullable = false)
//    private Integer idKota;
}
