package com.org.makgol.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PathConfig implements WebMvcConfigurer {
    @Value("${file.path.matcher}")
    private String filePathMatcher;
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        String currentDirectory = System.getProperty("user.dir");
//        System.out.println("현재 디렉토리: " + currentDirectory);
//        String uploadFolder = currentDirectory+"\\src\\main\\resources\\static\\image\\";
//        uploadFolder = uploadFolder.replace("\\","/");
//        registry.addResourceHandler(filePathMatcher+"**")
//                .addResourceLocations("file:"+uploadFolder);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("현재 디렉토리: " + currentDirectory);
        String uploadFolder = currentDirectory+"\\src\\main\\resources\\static\\image\\";
        uploadFolder = uploadFolder.replace("\\","/");
        registry.addResourceHandler(filePathMatcher+"**")
                .addResourceLocations("file:"+uploadFolder);
    }
}