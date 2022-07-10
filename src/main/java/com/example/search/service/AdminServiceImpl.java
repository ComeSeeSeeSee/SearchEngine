package com.example.search.service;

import com.example.search.entity.Admin;
import com.example.search.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository repository;


    @Override
    public Boolean findAdmin(Admin admin) {
        Optional<Admin> byId = repository.findById(admin.getId());
        if(byId.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Admin findByNameAndPassword(String name, String password) {
        Admin byNameAndPassword = repository.findByNameAndPassword(name, password);
        if(byNameAndPassword!=null){
            return byNameAndPassword;
        }

        return null;
    }


    @Override
    public Admin findById(Integer id) {
        Optional<Admin> byId = repository.findById(id);
        Admin admin = byId.get();
        return admin;
    }



    @Override
    public Boolean updatePasswordById(Integer id, String oldPassword, String newPassword) {

        int i = repository.updatePasswordByIdAndPassword(newPassword, id, oldPassword);

        if(i>0){
            return true;
        }

        return false;
    }


}
