package com.example.search.controller;

import com.example.search.entity.User;
import com.example.search.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping({"","/","/login"})
    public String indexLogin() {
        return "login";
    }

    @PostMapping("/loginPost")
    public String login(@RequestParam("username") String name, @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){
        User byNameAndPassword = userService.findByNameAndPassword(name, password);
        if(byNameAndPassword==null){
            model.addAttribute("login_error_msg","Username or password incorrect");
            return "login";
        }


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




        session.setAttribute("loginUsername",byNameAndPassword.getId());
        session.setAttribute("loginUserId",byNameAndPassword.getId());


        System.out.println("session stored!!!!!!");
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

        User distinctByName = userService.findDistinctByName(name);

        if (distinctByName==null){
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            userService.save(user);

            log.info(name + " saved");
            List<User> all = userService.findAll();
            all.forEach(System.out::println);
            return "login";
        }

        log.info(distinctByName.getName());
        model.addAttribute("register_error_msg","       Username exists, please try again");
        System.out.println("exists, please try again");
        return "register";
    }


    /*
    User management
     */
    @GetMapping({"/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "user/user_index";
    }

    @GetMapping("/bookmark")
    public String bookmark(){
        return "user/bookmark";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model){
        int loginUserId = (Integer)request.getSession().getAttribute("loginUsername");
        Optional<User> byId = userService.findById(loginUserId);
        User user = byId.get();

        System.out.println("inside profile");

        System.out.println(user);
        if (user == null) {
            return indexLogin();
        }

        request.setAttribute("path", "profile");
        request.setAttribute("username", user.getName());


        return "user/user_profile";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (!(StringUtils.hasLength(originalPassword) || StringUtils.hasLength(newPassword))) {
            return "Cannot be empty";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        System.out.println(loginUserId);
        System.out.println(newPassword);
        System.out.println(originalPassword);


        if(userService.updatePasswordById(newPassword,loginUserId,originalPassword)){

            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            indexLogin();
            return "success";
        }else {
            return "Failed";
        }
    }




    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "login";
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
