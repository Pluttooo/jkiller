package com.jkiller.killer.controller;

import com.jkiller.killer.basics.springboot.LibraryProperties;
import com.jkiller.killer.basics.springboot.MyProfileProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/jkiller")
public class BasicPracticeController {

    // 构造函数注入 LibraryProperties
    private final LibraryProperties libraryProperties;
    private final MyProfileProperties myProfileProperties;

    public BasicPracticeController(LibraryProperties libraryProperties, MyProfileProperties myProfileProperties) {
        this.libraryProperties = libraryProperties;
        this.myProfileProperties = myProfileProperties;
    }

    // 配置文件读取优先级 先读/config文件夹 再读resource/config文件夹 最后读/resource文件夹
    @ResponseBody
    @RequestMapping(value = "/get_app_info", method = RequestMethod.GET)
    public LibraryProperties GetAppInfo() {
        return libraryProperties;
    }

    @ResponseBody
    @RequestMapping(value = "/get_profile_info", method = RequestMethod.GET)
    public MyProfileProperties GetProfileInfo() {
        return myProfileProperties;
    }

    @ResponseBody
    @RequestMapping(value = "/my_filter", method = RequestMethod.GET)
    public String getFilterMessage() throws InterruptedException{
        Thread.sleep(500);
        return "Hello";
    }

}
