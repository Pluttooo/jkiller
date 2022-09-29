package com.jkiller.killer.basics.springboot;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(value = "my-profile")
public class MyProfileProperties {

    @NotNull
    private String name;

    @NotNull
    private String email;
}
