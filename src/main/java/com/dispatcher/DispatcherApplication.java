package com.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@MapperScan(basePackages = "com.example.sl.demospringboot.dao")//这个注解注意一下 放DAO层的包名 对这个包下进行注入
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DispatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(DispatcherApplication.class, args);
    }
}
