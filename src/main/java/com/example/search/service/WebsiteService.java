package com.example.search.service;




import com.example.search.entity.Website;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface WebsiteService {
    Website save(Website website);

    boolean deleteById(Integer id);

    Website findById(Integer id);

    boolean updateById(Integer id);

    Set<Website> findAll();

    Website findByDescription(String description);





    Page<Website> findWebsiteByDescriptionIsContaining(String name, Pageable pageable);

    Page<Website> findAllByPages(Pageable pageable);
}
