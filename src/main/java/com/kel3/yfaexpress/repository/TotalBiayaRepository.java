package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.TotalBiaya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalBiayaRepository extends JpaRepository<TotalBiaya, Integer> {
}
