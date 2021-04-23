package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.BeratBarang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeratBarangRepository extends JpaRepository<BeratBarang, Integer> {
    List<BeratBarang> findAllByIdBeratBarang(Integer idBeratBarang);
}
