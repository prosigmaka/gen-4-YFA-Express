package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class PengirimDto {
    private Integer idPengirim;
    private String namaPengirim;
    private Number telpPengirim;
    //private String kotaPengirim;
    private String alamatPengirim;
    private Number kodePosPengirim;
}
