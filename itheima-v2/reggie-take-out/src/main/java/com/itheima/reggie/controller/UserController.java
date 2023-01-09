package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.User;
import com.itheima.reggie.mapper.UserMapper;
import com.itheima.reggie.service.UserService;
import com.itheima.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: daonian
 * @date: 2023/01/09 20:32
 */
@Slf4j
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 生成并发送验证码
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/send_code", method = RequestMethod.GET)
    public BaseResponse<String> sendCode(HttpSession httpSession) {
        try {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("ValidateCode={}", code);
            // SMSUtils.sendMessage(
            //         "[测试]reggie-take-out-code",
            //         code, "fanchaofeng723@gmail.com");
            httpSession.setAttribute("ValidateCode", code);
            return BaseResponse.success("验证码发送成功");
        } catch (Exception e) {
            return BaseResponse.error("验证码发送失败");
        }
    }

    /**
     * 校验并登录
     * @param requestBody
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse<String> login(@RequestBody Map<String, Object> requestBody,
                                      HttpSession httpSession) {
        log.info("loginRequestBody={}", requestBody.toString());
        String validateCode = requestBody.get("validate_code").toString();
        String userPhone = requestBody.get("phone").toString();
        // 获取session里的code
        String validateCodeInSession = httpSession.getAttribute("ValidateCode").toString();

        // 对比验证码
        if (validateCodeInSession != null && validateCodeInSession.equals(validateCode)) {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getPhone, userPhone);
            User user = userMapper.selectOne(userLambdaQueryWrapper);
            // 如果是新用户 直接注册登录
            if (ObjectUtils.isEmpty(user)) {
                user = new User();
                user.setPhone(userPhone);
                user.setStatus(1);
                userMapper.insert(user);
            }
            httpSession.setAttribute("userId", user.getId());
            return BaseResponse.success("登录成功");
        }
        return BaseResponse.error("验证码输入不正确");
    }
}
