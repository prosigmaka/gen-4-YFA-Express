//package com.kel3.yfaexpress.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import com.kel3.yfaexpress.model.entity.Admin;
//
//public interface AdminRepository extends JpaRepository<Admin, Integer>{
//    Optional<Admin>
//    findIdAdmin(Integer IdAdmin);
//    Admin findbyAdminname(String username);
//    Admin findByIdAdminAndIsDelete(Integer id, boolean b);
//    List<Admin> findAllByisDelete(Boolean fas);
//}
