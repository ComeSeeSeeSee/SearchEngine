package com.example.search.repository;


import com.example.search.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, Integer> {

}