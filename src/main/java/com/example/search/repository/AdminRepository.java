package com.example.search.repository;

import com.example.search.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {


    Admin findByNameAndPassword(String name, String password);


//    Admin updatePasswordById(Integer id, String oldPassword, String newPassword);


    @Transactional
    @Modifying
    @Query("update Admin a set a.password = ?1 where a.id = ?2 and a.password = ?3")
    int updatePasswordByIdAndPassword(String newPassword, Integer id, String oldPassword);





}