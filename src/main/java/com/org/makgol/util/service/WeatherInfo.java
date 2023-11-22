package com.org.makgol.util.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherInfo {
    public List<Integer> findCoordinate(String address){
        List<Integer> coordinate = new ArrayList<>();
        return coordinate;
    }
}
