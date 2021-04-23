package com.kel3.yfaexpress.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kel3.yfaexpress.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User>
    findByIdUser(Integer idUser);
    User findbyUsername(String username);
    User findByIdUserAndIsDelete(Integer id, boolean b);
    List<User> findAllByisDelete(Boolean fas);

}
