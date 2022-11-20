package com.itheima.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "server-info")
public class ServerInfo {
    private String ipAddress;
    private long id;
    private String location;
}
