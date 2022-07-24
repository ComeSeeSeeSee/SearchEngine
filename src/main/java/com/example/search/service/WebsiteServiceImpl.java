package com.example.search.service;



import com.example.search.entity.Website;
import com.example.search.repository.WebsitePageAndSortRepository;
import com.example.search.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    @Override
    public Website save(Website website) {
       return websiteRepository.save(website);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(websiteRepository.existsById(id)){
            websiteRepository.deleteById(id);
            return true;
        }
       return false;
    }

    @Override
    public Website findById(Integer id) {
        if(websiteRepository.getById(id)!=null){
            return websiteRepository.getById(id);
        }
        return new Website("does not exist","does not exist","does not exist");
    }

    @Override
    public Set<Website> findAll() {
        List<Website> all = websiteRepository.findAll();

        Set<Website> set = new LinkedHashSet<>();

        for (int i = 0; i < all.size(); i++) {
            set.add(all.get(i));
        }

        return set;
    }

    @Override
    public Website findByDescription(String description) {
        List<Website> all = websiteRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
           if(all.get(i).getDescription().contains(description)) {
               return all.get(i);
           }
        }
        return null;
    }





    @Autowired
    WebsitePageAndSortRepository websitePageAndSortRepository;


    @Override
    public Page<Website> findWebsiteByDescriptionIsContaining(String name, Pageable pageable) {

        try {
            Page<Website> websiteByDescriptionIsContaining = websitePageAndSortRepository.findWebsiteByDescriptionIsContaining(name, pageable);
            return websiteByDescriptionIsContaining;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Page<Website> findAllByPages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Website> all = websitePageAndSortRepository.findAll(pageable);

        return all;
    }


    @Override
    public boolean updateById(Integer id){
        return  false;
    }


}
