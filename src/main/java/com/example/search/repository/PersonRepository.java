package com.example.search.repository;


import com.example.search.entity.User;
import org.springframework.data.repository.Repository;


/**
 * this is for customized repository query
 */
public interface PersonRepository extends Repository<User,Integer> {


    User findDistinctByName(String name);


    User findByNameAndPassword(String name, String password);


}
