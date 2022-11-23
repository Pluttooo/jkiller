package com.itheima.pojo;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties(prefix = "server-info")
@Validated
public class ServerInfo {
    @NotNull(message = "ip不能为空")
    private String ipAddress;
    private long id;
    private String location;
}
