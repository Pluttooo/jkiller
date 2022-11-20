package com.itheima.controller;

import com.itheima.pojo.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @ResponseBody
    @RequestMapping(value = "/get_book", method = RequestMethod.GET)
    public void getBooks() {
    }

    @ResponseBody
    @RequestMapping(value = "/server_info", method = RequestMethod.GET)
    public void getServerInfo() {
        ServerInfo serverInfo = new ServerInfo();
        logger.info("server info = " + serverInfo);
    }
}
