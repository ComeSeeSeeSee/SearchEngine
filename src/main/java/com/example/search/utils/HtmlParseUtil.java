package com.example.search.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class HtmlParseUtil {

    public static void main(String[] args) throws IOException {

        //get request
        //https://www.baeldung.com/spring-data-elasticsearch-tutorial
        String url = "https://www.google.com/search?q=java";
        // Parse web pages (returned the document object is .js page)
        Document document = Jsoup.parse(new URL(url), 30000);
        //able to use all method in js
        Element elementById = document.getElementById("search");
        System.out.println(elementById);
        //get all li elements



    }

}
