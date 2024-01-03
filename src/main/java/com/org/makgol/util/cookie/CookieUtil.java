package com.org.makgol.util.cookie;

import com.org.makgol.users.vo.UsersResponseVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CookieUtil {
    // 모든 경로에 쿠키 저장
    public static void setCookie(HttpServletResponse res, String key, String value) {
        String encodeValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
        //String cookieValue = String.format("%s = %s; Max-Age=1800; Path=/; HttpOnly; Secure; SameSite=Strict;", key, encodeValue);
        String cookieValue = String.format("%s = %s; Max-Age=1800; Path=/; HttpOnly; SameSite=lax;", key, encodeValue);
        res.addHeader("Set-Cookie", cookieValue);
    }


    // 지정한 경로에 쿠키 저장
//    public static void setImportantCookie(HttpServletResponse res, String key, String value) {
//        String encodeValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
//        String cookieValue = String.format("%s = %s; Max-Age=1800; Path=/user/**; HttpOnly; Secure; SameSite=Strict;", key, encodeValue);
//        res.addHeader("Set-Cookie", cookieValue);
//    }

    // 쿠키에 담겨있는 값 가져오기
    public static Map<String, List<String>> getCookie(HttpServletRequest req) {
        List<String> name = new ArrayList<>();
        List<String> value = new ArrayList<>();
        Cookie[] cookies = req.getCookies(); // 쿠키들
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                name.add(cookie.getName());
                value.add(URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));
            }
        }

        Map<String, List<String>> map = new HashMap<>();
        map.put("name", name);
        map.put("value", value);
        return map;
    }


    public static void clearCookie(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if (cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setPath("/");
                cookies[i].setMaxAge(0);
                res.addCookie(cookies[i]);
            }
        }
    }

    public static boolean saveCookies(HttpServletResponse response, UsersResponseVo loginedUserVo) {
        CookieUtil.setCookie(response, "id", String.valueOf(loginedUserVo.getId()));
        CookieUtil.setCookie(response, "name", loginedUserVo.getName());
        CookieUtil.setCookie(response, "photo_path", loginedUserVo.getPhoto_path());
        CookieUtil.setCookie(response, "grade", loginedUserVo.getGrade());
        CookieUtil.setCookie(response, "userX", String.valueOf(loginedUserVo.getLongitude()));
        CookieUtil.setCookie(response, "userY", String.valueOf(loginedUserVo.getLatitude()));
        CookieUtil.setCookie(response, "weatherAddr", String.valueOf(loginedUserVo.getWeatherAddr()));
        CookieUtil.setCookie(response, "valueX", String.valueOf(loginedUserVo.getValueX()));
        CookieUtil.setCookie(response, "valueY", String.valueOf(loginedUserVo.getValueY()));
        return true;
    }
}