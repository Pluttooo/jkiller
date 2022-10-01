package com.jkiller.killer.controller;

import com.jkiller.killer.basics.springboot.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/api/jkiller")
public class ExceptionTestController {

    @ResponseBody
    @RequestMapping(value = "/illegalArgumentException", method = RequestMethod.GET)
    public void throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }

    @ResponseBody
    @RequestMapping(value = "/resourceNotFoundException", method = RequestMethod.GET)
    public void throwResourceNotFoundException() {
        // throw new ResourceNotFoundException();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found", new ResourceNotFoundException());
    }
}
