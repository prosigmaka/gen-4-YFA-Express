package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Kota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KotaRepository extends JpaRepository<Kota, Integer> {
    List<Kota> findAllByIdKota(Integer idKota);
}
