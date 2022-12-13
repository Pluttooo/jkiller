package com.itheima.reggie.controller;

import com.itheima.reggie.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Controller
@RequestMapping(value = "/api/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 上传
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public BaseResponse<String> upload(MultipartFile file) {
        log.info("上传的文件: {}", file.toString());
        // 获取文件原始文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件扩展名
        String fileSuffix = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        if (StringUtils.isEmpty(fileSuffix)) {
            return BaseResponse.error("未获取到文件扩展名");
        }
        // 使用uuid生成一个新的文件名 防止文件名重复 文件被覆盖
        String newFileName = UUID.randomUUID().toString() + fileSuffix;
        // 创建一个目录对象
        File dir = new File(basePath);
        // 判断目录是否存在
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + newFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResponse.success(newFileName);
    }

    /**
     * 下载
     * @param name
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(String name, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
