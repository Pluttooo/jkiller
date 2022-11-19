package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @ResponseBody
    @RequestMapping(value = "/get_book", method = RequestMethod.GET)
    public void getBooks() {

    }
}
