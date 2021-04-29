package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Barang;
import com.kel3.yfaexpress.model.entity.Useraa;
import org.hibernate.persister.walking.spi.CollectionElementDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Useraa, Long>{
	Useraa findByEmail(String email);
}
