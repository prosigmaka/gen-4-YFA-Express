package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = Provinsi.TABLE_PROVINSI)
public class Provinsi {
    public static final String TABLE_PROVINSI = "t_provinsi";

    @Id
    private String provinceId;
    private String province;
}
