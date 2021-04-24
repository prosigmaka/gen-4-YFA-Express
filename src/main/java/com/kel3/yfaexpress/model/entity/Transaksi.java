package com.kel3.yfaexpress.model.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Transaksi.TABLE_NAME)
public class Transaksi {
    public static final String TABLE_NAME = "t_transaksi";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME , sequenceName = "t_transaksi_seq")
    private Integer idTransaksi;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;
    @Column(name = "id_user")
    private Integer idUser;

    @OneToOne
    @JoinColumn(name = "id_barang", insertable = false, updatable = false)
    private Barang barang;
    @Column(name = "id_barang")
    private Integer idBarang;

    private String statusDelivery;
    private String statusPayment;

//    @ManyToOne
//    @JoinColumn(name = "id_total_biaya", insertable = false, updatable = false)
//    private TotalBiaya totalBiaya;
    @Column(name = "id_total_biaya")
    private Integer idTotalBiaya;


    @ManyToOne
    @JoinColumn(name = "id_kurir", insertable = false, updatable = false)
    private Kurir kurir;
    @Column(name = "id_kurir")
    private Integer idKurir;
}
