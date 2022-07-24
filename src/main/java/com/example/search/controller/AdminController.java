
package com.example.search.controller;


import com.example.search.entity.Admin;
import com.example.search.entity.Website;
import com.example.search.service.AdminService;
import com.example.search.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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


    /*
    website part
     */



    @Autowired
    private WebsiteService websiteService;



    @GetMapping("/addWebsite")
    public String addWebsite(){
        return "admin/websiteAdd";
    }





    @PostMapping("/saveWebsite")
    public String saveWebsite(@RequestParam("description")String description,@RequestParam("url") String url,Model model){


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
    @GetMapping("/webistesConfig")
    public String websiteAll(Model model){
//        return "admin/websiteConfig";
        return findPaginated(1, model);
    }


    @PostMapping("/searchWebsite")
    public String searchWebsite(Model model,@RequestParam("keyword")String description){
        //fake search, add it later
        model.addAttribute("description",description);
        return findPaginated(1,model);
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        int pageSize = 10;

        String description =(String)model.getAttribute("description");

        System.out.println(description);

        if(description!=null){
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Website> page = websiteService.findWebsiteByDescriptionIsContaining(description, pageable);
            List<Website> websiteList = page.getContent();

            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("listWebsite", websiteList);
            return "admin/websiteConfig";
        }

        Page<Website> page = websiteService.findAllByPages(pageNo,pageSize);
        List<Website> websiteList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listWebsite", websiteList);
        return "admin/websiteConfig";

    }



    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Website website = websiteService.findById(id);
        model.addAttribute("url",website.getUrl());
        model.addAttribute("description",website.getDescription());

        return "admin/updateWebiste";
    }

    @GetMapping("/deleteWebsite/{id}")
    public String deleteEmployee(@PathVariable(value = "id") int id, Model model) {

        boolean b = websiteService.deleteById(id);
        System.out.println(b);

        return findPaginated(1, model);
    }







    @GetMapping("/goToIndex")
    public String gotoIndex(){
        return "index";
    }


}
