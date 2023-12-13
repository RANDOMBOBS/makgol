package com.org.makgol.common.filter;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = sanitize(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return sanitize(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return sanitize(value);
    }

    private String sanitize(String value) {
        // 여기에서 XSS 방지 로직을 추가
        // 예제로는 간단한 방식으로 모든 HTML 태그를 제거함
        if (value != null) {
            // HTML 태그 제거
            String sanitizedValue = Jsoup.clean(value, Whitelist.none());

            // 여러 가지 방법으로 특수 문자를 치환하거나 추가적인 처리를 할 수 있음
            // 예를 들어, &를 치환하려면 sanitizedValue = sanitizedValue.replace("&", "&amp;");

            return sanitizedValue;
        }
        return null;
    }
}
