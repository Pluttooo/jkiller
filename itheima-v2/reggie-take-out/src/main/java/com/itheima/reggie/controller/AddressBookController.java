package com.itheima.reggie.controller;

import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.AddressBook;
import com.itheima.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: daonian
 * @date: 2023/01/10 16:45
 */
@Slf4j
@Controller
@RequestMapping(value = "/api/address")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse<String> addAddress(@RequestBody AddressBook addressBook) {
        log.info("new address={}", addressBook);
        return addressBookService.addAddress(addressBook);
    }

    @ResponseBody
    @RequestMapping(value = "/set_default", method = RequestMethod.POST)
    public BaseResponse<String> setAsDefaultAddress(@RequestBody AddressBook addressBook) {
        return addressBookService.setAsDefaultAddress(addressBook);
    }

    @ResponseBody
    @RequestMapping(value = "/get_addr_by_id", method = RequestMethod.GET)
    public BaseResponse<AddressBook> getAddrById(@RequestParam(value = "id") Long id) {
        return addressBookService.getAddressById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/addr_list", method = RequestMethod.POST)
    public BaseResponse<List<AddressBook>> getAddressList(@RequestBody AddressBook addressBook) {
        return addressBookService.getAddressListByUserId(addressBook);
    }
}
