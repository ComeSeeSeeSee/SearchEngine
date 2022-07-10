package com.example.search.service;

import com.example.search.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> findById(Integer id);


    List<User> findAll();
    User save(User user);
    void delete(Integer id);

    Page<User> findAll(Pageable pageable);

    List<User> findByNameLike(String name);



    User findDistinctByName(String name);



    User findByNameAndPassword(String name, String password);

}
