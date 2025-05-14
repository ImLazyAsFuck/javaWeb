package com.ss8.config;


import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class[] {WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings(){
        return new String[] {"/"};
    }
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}
