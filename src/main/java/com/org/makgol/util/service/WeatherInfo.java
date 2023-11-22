package com.org.makgol.util.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherInfo {
    public List<Integer> findCoordinate(String address){
        // 유저 주소가 넘어옴! (서울 강남구 강남대로 476)
        List<Integer> coordinate = new ArrayList<Integer>();

        return coordinate;
    }
}
