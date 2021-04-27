package com.kel3.yfaexpress.model.entity;

import lombok.Data;
import com.kel3.yfaexpress.model.entity.BeratBarang;
import com.kel3.yfaexpress.model.entity.Layanan;

import javax.persistence.*;

@Entity
@Table(name = Barang.TABLE_TOTAL_BIAYA)
@Data
public class TotalBiaya {
    public static final String TABLE_TOTAL_BIAYA = "t_total_biaya";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLE_TOTAL_BIAYA)
    @SequenceGenerator(name = TABLE_TOTAL_BIAYA, sequenceName = "t_total_biaya_seq")
    private Integer idTotal;
    //private Integer idJarak;


    @ManyToOne
    @JoinColumn(name = "id_berat_barang", insertable = false, updatable = false)
    private BeratBarang beratBarang;

    @Column(name = "id_berat_barang", nullable = false)
    private Integer idBeratBarang;

    @ManyToOne
    @JoinColumn(name = "id_layanan", insertable = false, updatable = false)
    private Layanan biayaLayanan;

    @Column(name = "id_layanan", nullable = false)
    private Integer idLayanan;

    private Integer totalBiaya;
}
