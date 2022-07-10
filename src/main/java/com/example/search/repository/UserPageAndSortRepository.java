package com.example.search.repository;

import com.example.search.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPageAndSortRepository extends PagingAndSortingRepository<User,Integer> {

    Page<User> findByNameLike(String name, Pageable pageable)throws Exception;
}
