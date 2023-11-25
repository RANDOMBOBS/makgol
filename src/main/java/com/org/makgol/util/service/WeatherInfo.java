package com.org.makgol.util.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.*;

@Component
public class WeatherInfo {
    public List<Integer> findCoordinate(String address){
        System.out.println("address = " + address);
        List<Integer> coordinate = new ArrayList<Integer>();
        try {
            FileReader fileReader = new FileReader("src\\main\\resources\\static\\csv\\기상청41_단기예보 조회서비스_오픈API활용가이드_격자_위경도(20230611).csv", Charset.forName("EUC-KR"));
            BufferedReader file = new BufferedReader(fileReader);
            String line = "";
            List<List<String>> apiList = new ArrayList<>();
            List<String> cityName = new ArrayList<>();
            int no = 0;
            while ((line = file.readLine()) != null) {
                List<String> aLine = new ArrayList<>();
                String[] addressInfo = line.split(",");
                for (String s : addressInfo) {
                    aLine.add(s);
                }
                apiList.add(aLine);
            }
            for(int i=0; i<apiList.size(); i++){
               String city1 = apiList.get(i).get(0);
               String city2 = apiList.get(i).get(1);
               cityName.add(city1+" "+city2);
            }
            for(int i=0; i<cityName.size(); i++){
                if(address.contains(cityName.get(i))){
                    no = i;
                }
            }
              String x = apiList.get(no).get(2);
              String y = apiList.get(no).get(3);
              coordinate.add(Integer.parseInt(x));
              coordinate.add(Integer.parseInt(y));
            System.out.println("csv 파일에서 구한 좌표값 = "+coordinate);
        } catch (Exception e){
            e.printStackTrace();
        }
        return coordinate;
    }
}
