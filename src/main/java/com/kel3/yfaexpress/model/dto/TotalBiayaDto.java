package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class TotalBiayaDto {
    private Integer idTotalBiaya;

    private Integer idBeratBarang;
    private String kategoriBeratBarang;
    private String biayaKategori;

    private Integer idLayanan;
    private String kategoriLayanan;
    private Integer biayaLayanan;

    private Integer totalBiaya;
}
