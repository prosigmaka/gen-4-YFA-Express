package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Pengirim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PengirimRepository extends JpaRepository<Pengirim, Integer> {

    List<Pengirim> findAllByIdPengirim(Integer idPengirim);
}
