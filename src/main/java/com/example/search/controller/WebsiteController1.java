package com.example.search.controller;

import com.example.search.entity.Website;
import com.example.search.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


// using restful

//@EnableWebMvc // auto config json
@Controller
@RequestMapping("/web")
public class WebsiteController1 {

    @Autowired
    WebsiteService websiteService;


    private String desc;

    //Response page
    @RequestMapping({"/","/index",""})
    public String indexPage() {
        System.out.println("Index Page........");
        return "index";
    }


    /*
    Search function method
     */
    @PostMapping("/getByDescription/{pageNo}")
    public String getByDescriptionPage(@RequestParam("keyword") String description, @PathVariable(value = "pageNo") int pageNo, Model model){

        PageRequest of = PageRequest.of(pageNo, 5);

        if(description!=null){
            Page<Website> websitesPage = websiteService.findWebsiteByDescriptionIsContaining(description, of);

            if(websitesPage.hasContent()){
                List<Website> content = websitesPage.getContent();

                System.out.println(content);

                model.addAttribute("desc",description);
//            model.addAttribute("urls",content.get(0).getUrl());
                desc = description;

                model.addAttribute("listEmployees", content);
                model.addAttribute("currentPage", 0);
                model.addAttribute("totalPages", websitesPage.getTotalPages());
                model.addAttribute("totalItems", websitesPage.getTotalElements());

                return "searchPage";
            }else {
                return "searchP";
            }
        }

        return "index";
    }



    @GetMapping("/getByDescription2/{pageNo}")
    public String viewByDescriptionPage(@PathVariable(value = "pageNo") int pageNo, Model model) {
        System.out.println(desc);
        return getByDescriptionPage(desc,pageNo, model);
    }













    //save
//    @RequestMapping(value = "/saveJson", method = RequestMethod.POST)
    @PostMapping("/save")
    @ResponseBody
    public boolean listJsonTry(@RequestBody Website website){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(websiteService.save(new Website(website.getDescription(),website.getUrl(), dtf.format(now)))!=null){
            return true;
        }
        //http://localhost:8080/web  but post  and add json date into
        return false;
    }


    //delte by id
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public boolean deleteWebsites(@PathVariable Integer id){
        boolean b = websiteService.deleteById(id);
        System.out.println(b);
        // http://localhost:8080/web/2  but delete
        return b;
    }








//
//    //http://localhost:8080/web/getByDescription?description=cs
//    //find by description 5
//    @PostMapping("/getByDescription")
//    public String getByDescription(@RequestParam("keyword") String description, Model model){
//
//        PageRequest of = PageRequest.of(0, 5);
//        if(description!=null){
//            Page<Website> websitesPage = websiteService.findWebsiteByDescriptionIsContaining(description, of);
//            List<Website> content = websitesPage.getContent();
//
//            System.out.println(content);
//
//            model.addAttribute("desc",description);
////            model.addAttribute("urls",content.get(0).getUrl());
//
//
//
//            model.addAttribute("listEmployees", content);
//            model.addAttribute("currentPage", 0);
//            model.addAttribute("totalPages", websitesPage.getTotalPages());
//            model.addAttribute("totalItems", websitesPage.getTotalElements());
//
//            return "searchPage.html";
//        }
//        return "user_index.html";
//    }

//
//    @PostMapping("/getByDescription1")
//    public String getByDescription1(@RequestParam("keyword") String description, Model model){
//
//        int pageNo = 0;
//
//
//        PageRequest of = PageRequest.of(pageNo, 5);
//
//
//        Page<Website> websitesPage = websiteService.findWebsiteByDescriptionIsContaining(description, of);
//        List<Website> content = websitesPage.getContent();
//
//        System.out.println(content);
//
////            model.addAttribute("desc",content.get(0).getDescription());
////            model.addAttribute("urls",content.get(0).getUrl());
//
//
//        model.addAttribute("listEmployees", content);
//        model.addAttribute("currentPage", 0);
//        model.addAttribute("totalPages", websitesPage.getTotalPages());
//        model.addAttribute("totalItems", websitesPage.getTotalElements());
//
//        return "searchPage.html";
//
//
//    }





//
//    //Find all
//    @GetMapping
//    @ResponseBody
//    public Set<Website> getALLWebsites(){
//        Set<Website> all = websiteService.findAll();
//        //http://localhost:8080/web   get
//        return  all;
//    }
//
//    //Find all by page
//    @GetMapping("/websitesAllP")
//    @ResponseBody
//    public Page<Website> getALLWebsitesByPage(){
//        PageRequest of = PageRequest.of(0, 5);
//        //http://localhost:8080/web/websitesAllP
//        return websiteService.findAllByPages(of);
//    }
//
//
//    //find website by id
////    @RequestMapping(value = "/saveJson", method = RequestMethod.GET)
//    //how to deal with null value???
//    @GetMapping("{id}")
//    @ResponseBody
//    public String getWebsites(@PathVariable Integer id){
//        Website byId = websiteService.findById(id);
//        if(byId==null){
//            return "does not exist";
//        }
//        return byId.toString();
//        //http://localhost:8080/web/2
//    }
//


















    //    @PutMapping("/update")
//    @ResponseBody
//    public boolean updateWebsitesById(@RequestBody Website website){
//        return websiteService.updateById(website.getId());
//    }

        //??? pass path variables
    // localhost:8080/web/websiteByDes?description=cs
//    @GetMapping("/websiteByDess/{description}/{pageNum}/{pageSize}")
//    @ResponseBody
//    public Page<Website> commonParamn(@PathVariable("description") String description,
//                                      @PathVariable("pageNum") int pageNum,
//                                      @PathVariable("pageSize") int pageSize){
//
//        if(pageNum<1){
//            pageNum = 1;
//        }
//        if(pageSize<1){
//            pageSize = 1;
//        }
//        PageRequest of = PageRequest.of(pageNum-1, 3);
//
//        if(description!=null){
//            Page<Website> websiteByDescriptionIsContaining = websiteService.findWebsiteByDescriptionIsContaining(description, of);
//            return websiteByDescriptionIsContaining;
//        }
//        return websiteService.findWebsiteByDescriptionIsContaining(" ", of);
//
//    }




}
