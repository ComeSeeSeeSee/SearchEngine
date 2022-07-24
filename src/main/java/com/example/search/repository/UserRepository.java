package com.example.search.repository;

import com.example.search.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select u from User u where u.name like concat('%',?1,'%')")
    List<User> findByNameLike(String name);

    @Query(value = "SELECT * FROM user u WHERE u.name like '%'|| ?1 || '%'",
            nativeQuery = true)
    List<User> findByNameLikeNativeQuery(String name);

    @Query("select u from User u where u.name like concat('%',:name,'%')")
    List<User> findByNameLikeParam(@Param("name")String name);



    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.password = ?2 and u.id = ?3")
    int updatePasswordByPasswordAndId(String newpassword, String oldpassword, Integer id);






}