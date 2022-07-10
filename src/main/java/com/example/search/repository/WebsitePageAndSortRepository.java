package com.example.search.repository;

import com.example.search.entity.Website;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WebsitePageAndSortRepository extends PagingAndSortingRepository<Website,Integer> {


    Page<Website> findWebsiteByDescriptionIsContaining(String name, Pageable pageable)throws Exception;

//
//    boolean updateById(Integer id);






}
