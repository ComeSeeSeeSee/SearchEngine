package com.example.search.controller;

import com.example.search.entity.User;
import com.example.search.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping({"","/","/login"})
    public String indexLogin() {
        return "login";
    }

    @PostMapping("/loginPost")
    public String login(@RequestParam("username") String name, @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response, Model model){

        String remember = request.getParameter("remember");
        if("1".equals(remember)){
            //send cookie if checked
            Cookie cUsername = new Cookie("username",name);
            Cookie cPassword = new Cookie("password", password);

            cUsername.setMaxAge(60*60*24);
            cUsername.setMaxAge(60*60*24);

            response.addCookie(cUsername);
            response.addCookie(cPassword);
        }

        User byNameAndPassword = service.findByNameAndPassword(name, password);
//        log.info(byNameAndPassword.toString());

        if(byNameAndPassword!=null){
            return "/index";
        }else {
            model.addAttribute("login_error_msg","Username or password incorrect");
            return "login";
        }

    }

    @GetMapping({ "/register.html", "/register"})
    public String indexRegister(HttpServletRequest request) {
        request.setAttribute("path1", "register");
        return "register";
    }



    @PostMapping("/save")
    public String saveUser(@RequestParam("username") String name, @RequestParam("password")String password,Model model){

        User distinctByName = service.findDistinctByName(name);

        if (distinctByName==null){
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            service.save(user);

            log.info(name + " saved");
            List<User> all = service.findAll();
            all.forEach(System.out::println);
            return "login";
        }

        log.info(distinctByName.getName());
        model.addAttribute("register_error_msg","       Username exists, please try again");
        System.out.println("exists, please try again");
        return "register";
    }









//    @GetMapping("/alluser")
//    public String getAllUser(){
//        List<User> all = service.findAll();
//        System.out.println(all);
//        return all.toString();
//    }
//
//    @GetMapping(path = "/all")
//    @ResponseBody
//    public Iterable<User> getAllUsers() {
//        return service.findAll();
//    }
//
//    @GetMapping(path = "/info")
//    @ResponseBody
//    public Optional<User> findOne(@RequestParam Integer id) {
//        return service.findById(id);
//    }
//
//    @GetMapping(path = "/delete")
//    public void delete(@RequestParam Integer id) {
//        service.delete(id);
//    }
//
//
//    @GetMapping(path = "/page")
//    @ResponseBody
//    public Page<User> getAllUserByPage() {
//        PageRequest of = PageRequest.of(1, 3);
//        Page<User> all = service.findAll(of);
//        return all;
//    }




}
