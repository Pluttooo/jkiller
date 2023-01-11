package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.AddressBook;
import com.itheima.reggie.mapper.AddressBookMapper;
import com.itheima.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author: daonian
 * @date: 2023/01/10 16:43
 */
@Slf4j
@Service
public class AddressBookServiceImpl implements AddressBookService {

    private static final Long userId = 1417012167126876162L;

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public BaseResponse<String> addAddress(AddressBook addressBook) {
        addressBook.setUserId(userId);
        addressBook.setCreateUser(userId);
        addressBook.setUpdateUser(userId);
        try {
            int count = addressBookMapper.insert(addressBook);
            if (count < 0) {
                return BaseResponse.error("地址新增失败");
            }
            return BaseResponse.success("地址新增成功");
        } catch (Exception e) {
            return BaseResponse.error(e.getMessage());
        }
    }

    @Override
    public BaseResponse<String> setAsDefaultAddress(AddressBook addressBook) {
        addressBook.setUserId(userId);
        addressBook.setCreateUser(userId);
        addressBook.setUpdateUser(userId);

        LambdaUpdateWrapper<AddressBook> addressBookLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        addressBookLambdaUpdateWrapper.eq(AddressBook::getUserId, userId);
        addressBookLambdaUpdateWrapper.set(AddressBook::getIsDefault, 0);
        try {
            addressBookMapper.update(null, addressBookLambdaUpdateWrapper);

            addressBook.setId(1613158582864375809L);
            addressBook.setIsDefault(1);
            int count = addressBookMapper.updateById(addressBook);
            if (count > 0) {
                return BaseResponse.success("设置成功");
            } else {
                return BaseResponse.error("设置失败");
            }
        } catch (Exception e) {
            return BaseResponse.error(e.getMessage());
        }
    }

    @Override
    public BaseResponse<AddressBook> getAddressById(Long id) {
        AddressBook addressBook = addressBookMapper.selectById(id);
        if (ObjectUtils.isEmpty(addressBook)) {
            return BaseResponse.error("未查询到该地址");
        }
        return BaseResponse.success(addressBook);
    }

    @Override
    public BaseResponse<List<AddressBook>> getAddressListByUserId(AddressBook addressBook) {
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(addressBook.getUserId() != null, AddressBook::getUserId, addressBook.getUserId());
        List<AddressBook> addressBookList = addressBookMapper.selectList(addressBookLambdaQueryWrapper);
        return BaseResponse.success(addressBookList);
    }
}
