package com.itheima.reggie.service;

import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.AddressBook;

import java.util.List;

/**
 * @author: daonian
 * @date: 2023/01/10 16:43
 */
public interface AddressBookService {

    BaseResponse<String> addAddress(AddressBook addressBook);

    BaseResponse<String> setAsDefaultAddress(AddressBook addressBook);

    BaseResponse<AddressBook> getAddressById(Long id);

    BaseResponse<List<AddressBook>> getAddressListByUserId(AddressBook addressBook);
}
