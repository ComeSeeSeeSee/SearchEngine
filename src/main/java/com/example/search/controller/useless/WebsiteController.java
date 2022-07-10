package com.example.search.controller.useless;


import com.example.search.entity.Website;
import com.example.search.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


@EnableWebMvc // auto config json
//@RestController   // does not repsone html page  @Controller+@ResponseBody
@Controller
@RequestMapping("/website")
public class WebsiteController {

    @Autowired
    WebsiteService websiteService;


    @GetMapping("/websites/{id}")
    public String getWebsites(@PathVariable Integer id){
        Website byId = websiteService.findById(id);

        return byId.toString();
    }

    @GetMapping("/websitesAll")
    public String getALLWebsites(){
        Set<Website> all = websiteService.findAll();
        return  all.toString();
    }



    //pass parameters and responsebody
    @RequestMapping("/getByDescription")
    @ResponseBody
    public Page<Website> getByDescription(String description){
        PageRequest of = PageRequest.of(0, 5);
        if(description!=null){
            Page<Website> websiteByDescriptionIsContaining = websiteService.findWebsiteByDescriptionIsContaining(description, of);
            return websiteByDescriptionIsContaining;
        }

        return websiteService.findWebsiteByDescriptionIsContaining(" ", of);

    }

    //post request to save website
    @RequestMapping("/save")
    @ResponseBody
    public boolean saveWithDescriptionAndUrl(String description, String url){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Website website1 = new Website();

        website1.setCreateTime(dtf.format(now));
        website1.setDescription(description);
        website1.setUrl(url);

         if(websiteService.save(website1)!=null){
             return true;
         }
         return false;

    }


    // Using Json ................................

    //Json to save a website into database
    @RequestMapping("/saveJson")
    @ResponseBody
    public boolean listJsonTry(@RequestBody Website website){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(websiteService.save(new Website(website.getDescription(),website.getUrl(), dtf.format(now)))!=null){
            return true;
        }
        return false;
    }



    @RequestMapping("/saveJsonGroup")
    @ResponseBody
    public boolean listJsonTry(@RequestBody List<Website> websiteList){  // requestBody in front of the parameters to put data into request body
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < websiteList.size(); i++) {
                if(websiteService.save(new Website(websiteList.get(i).getDescription(),websiteList.get(i).getUrl(),dtf.format(now)))==null){
                   //should roll back transaction.???
                    return false;
                }
            }
        return true;
    }


    //Response page
    @RequestMapping( "/")
    public String indexPage() {
        System.out.println("Index Page");
        return "index";
    }

    //Response Json
    @RequestMapping("/all")
    @ResponseBody
    public Set<Website> toJsonWebSiteAll(){
        Set<Website> all = websiteService.findAll();
        return all;
    }



    @RequestMapping("/allpage")
    @ResponseBody
    public Page<Website> toJsonWebPageAllByPage(@RequestParam String description){
        PageRequest of = PageRequest.of(0, 4);
        return websiteService.findWebsiteByDescriptionIsContaining(description,of);
    }

























//    @RequestMapping(value = "/search")
//    public String search( Model model, @RequestParam(value = "keyword") String keyword){
//        Website byDescription = websiteService.findByDescription(keyword);
//        model.addAttribute("Description",byDescription.getDescription());
//        model.addAttribute("URL",byDescription.getUrl());
//
//        return "searchPage";
//    }
//
//    @GetMapping("websitess/{description}")
//    public String searchDes( @PathVariable String description){
//        Website byDescription = websiteService.findByDescription(description);
//
//        return byDescription.toString();
//    }
//
////    @GetMapping("aaa")
////    public String searchDess( ){
////        Website byDescription = websiteService.findByDescription("CS");
////
////        return byDescription.toString();
////    }



}
