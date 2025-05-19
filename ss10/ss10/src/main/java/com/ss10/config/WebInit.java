package com.ss10.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Các class cấu hình cho root context (ví dụ: cấu hình database, security)
        return null; // Hoặc trả về một mảng các class cấu hình
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Các class cấu hình cho DispatcherServlet context (ví dụ: cấu hình Spring MVC)
        return new Class<?>[] { AppConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // Mapping cho DispatcherServlet
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true); // Bắt buộc encoding cho cả request và response
        return new Filter[] { characterEncodingFilter };
    }
}