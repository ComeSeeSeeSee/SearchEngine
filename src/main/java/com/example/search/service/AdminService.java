package com.example.search.service;

import com.example.search.entity.Admin;

public interface AdminService {

    Boolean findAdmin(Admin admin);

    Admin findByNameAndPassword(String name, String password);


    Admin findById(Integer id);


    Boolean updatePasswordById(Integer id, String oldPassword, String newPassword);

}
