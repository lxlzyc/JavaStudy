package com.lxl.web;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Description: TODO
 * @author: lxl
 * @date: 2017/7/26 22:09
 * @version: V 1.0
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        Properties properties = new Properties();
        String active = "dev";
//        String active = "prod";
//        if (Host.getHostType() == HostType.PRODUCT) active = "prod";
        InputStream in = Application.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(in);
            properties.put("spring.profiles.active", active);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        SpringApplicationBuilder app = application.sources(this.getClass());
        app.properties(properties);
        return super.configure(application);
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream in = Application.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }
}

