package com.org.makgol.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PathConfig implements WebMvcConfigurer {
    @Value("${file.path.matcher}")
    private String filePathMatcher;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("현재 디렉토리: " + currentDirectory);
        String uploadFolder = currentDirectory+"\\src\\main\\resources\\static\\image\\";
        uploadFolder = uploadFolder.replace("\\","/");
        registry.addResourceHandler(filePathMatcher+"**")
                .addResourceLocations("file://"+uploadFolder);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        String currentDirectory = System.getProperty("user.dir");
//        System.out.println("현재 디렉토리: " + currentDirectory);
//        String uploadFolder = "/home/ubuntu/service/makgol/static/image/";
//        uploadFolder = uploadFolder.replace("\\","/");
//
//        System.out.println(uploadFolder);
//
//        registry.addResourceHandler(filePathMatcher+"**")
//                .addResourceLocations("file://"+uploadFolder);
//    }

    // 리눅스 경우 root에서 시작하는 폴더 경로 지정 할 경우
//.addResourceLocations("file:///usr/download/")

// 리소스 템플릿 경로를 지정할 경우
//.addResourceLocations("classpath:/templates/", "classpath:/static/")

// 윈도우에서 실행 시 다음과 같은 형태로 드라이브 문자 포함 경로 지정
//.addResourceLocations("file:///E:/webserver_storage/")
}