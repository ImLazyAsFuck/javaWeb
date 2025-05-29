package com.ss14.dto;

import com.ss14.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class UniqueProductNameInCookieValidator implements ConstraintValidator<UniqueProductNameInCookie, String>{

    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.trim().isEmpty()) {
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("products".equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();
                    List<Product> productList = parseProductsFromCookie(cookieValue);
                    for (Product p : productList) {
                        if (p.getName().equalsIgnoreCase(name)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private List<Product> parseProductsFromCookie(String cookieValue) {
        List<Product> productList = new ArrayList<>();
        if (!cookieValue.isEmpty()) {
            try {
                String decoded = URLDecoder.decode(cookieValue, StandardCharsets.UTF_8);
                String cleaned = decoded.replaceAll("^\\[|]$", "");
                String[] items = cleaned.split(",");
                for (String item : items) {
                    String[] parts = item.split("\\|");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());
                        productList.add(new Product(id, name, price));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productList;
    }
}

