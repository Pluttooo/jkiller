package com.itheima.mapper;

import com.itheima.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserMapper {

    List<User> selectAllUsers();
}
