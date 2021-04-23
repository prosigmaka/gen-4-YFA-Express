package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Layanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayananRepository extends JpaRepository<Layanan, Integer> {

}

