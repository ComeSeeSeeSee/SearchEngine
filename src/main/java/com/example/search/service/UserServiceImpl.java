package com.example.search.service;

import com.example.search.entity.User;
import com.example.search.repository.PersonRepository;
import com.example.search.repository.UserPageAndSortRepository;
import com.example.search.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findByNameLike(String name) {
//        return userRepository.findByNameLikeNativeQuery(name);
        return userRepository.findByNameLikeParam(name);
//        return userRepository.findByNameLike(name);
    }



    @Autowired
    UserPageAndSortRepository userPageAndSortRepository;




    @Autowired
    PersonRepository personRepository;




    @Override
    public User findDistinctByName(String name) {
        User distinctByName = personRepository.findDistinctByName(name);
        return distinctByName;
    }



    @Override
    public User findByNameAndPassword(String name, String password) {
        if(name!=null && password !=null){
            User byNameAndPassword = personRepository.findByNameAndPassword(name, password);


            return byNameAndPassword;
        }
        return null;

    }

    @Override
    public Boolean updatePasswordById(String newPassword, Integer id, String oldPassword) {

        int i = userRepository.updatePasswordByPasswordAndId(newPassword,oldPassword,id);

        if(i>0){
            return true;
        }
        return false;
    }
}
