
package com.example.search.controller;


import com.example.search.entity.Admin;
import com.example.search.entity.Website;
import com.example.search.service.AdminService;
import com.example.search.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }

    @GetMapping({"/test"})
    public String test() {
        return "admin/test";
    }


    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "admin/index";
    }
//
//    @GetMapping("/indexx")
//    public String indexx(HttpServletRequest request) {
//        request.setAttribute("path", "index");
//        return "admin/indexx";
//    }



    @PostMapping("/login1")
    public String login1(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpSession session,
                         Model model){

        System.out.println(StringUtils.hasLength(username));
        System.out.println(StringUtils.hasLength(password));

        if (!(StringUtils.hasLength(username) || StringUtils.hasLength(password))) {
            model.addAttribute("login_error_msg","Username or password cannot be empty");
            return "admin/login";
        }


        Admin byNameAndPassword = adminService.findByNameAndPassword(username, password);

        System.out.println(byNameAndPassword);

        if (byNameAndPassword!=null) {
            session.setAttribute("loginUserId",byNameAndPassword.getId());
            session.setAttribute("loginUser", username);
//            session.setAttribute("loginUserId", password);

            //session set time to expire
            return "admin/index";
        } else {
            model.addAttribute("login_error_msg","Username or password incorrect");
            return "admin/login";
        }


    }


    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model) {
        int loginUserId = (Integer)request.getSession().getAttribute("loginUserId");
        Admin adminUser = adminService.findById(loginUserId);

        System.out.println(adminUser);
        if (adminUser == null) {
            return "admin/login";
        }

        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getName());
//        request.setAttribute("nickName", adminUser.getNickName());

        return "admin/profile";
    }


    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (!(StringUtils.hasLength(originalPassword) || StringUtils.hasLength(newPassword))) {
            return "Cannot be empty";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");

        if(adminService.updatePasswordById(loginUserId,originalPassword,newPassword)){
            //Modified successfully后清空Session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
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
        return "admin/login";
    }



    @GetMapping("/addWebsite")
    public String addWebsite(){
        return "admin/websiteAdd";
    }


    @Autowired
    private WebsiteService websiteService;


    @PostMapping("/saveWebsite")
    public String saveWebsite(@RequestParam("description")String description,@RequestParam("url") String url){

        Website website = new Website();

        website.setDescription(description);
        website.setUrl(url);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        website.setCreateTime(dtf.format(now));

        System.out.println("hello");
        Website save = websiteService.save(website);
        if(save!=null){
            //logic here when true;

            return "admin/websiteAdd";
        }

        return "admin/websiteAdd";
    }

    @GetMapping("/goToIndex")
    public String gotoIndex(){
        return "index";
    }


}
