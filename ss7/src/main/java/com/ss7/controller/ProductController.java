package com.ss7.controller;

import com.ss7.model.Category;
import com.ss7.model.Product;
import com.ss7.service.CategoryService;
import com.ss7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    private List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();

        Product laptop = new Product(1, "Laptop Dell XPS 13", 1299.99, 10, 
                "High-performance laptop with 13-inch display", 
                "https://images.unsplash.com/photo-1593642632823-8f785ba67e45", 1);
        laptop.setColor("silver");
        laptop.setSize("13-inch");
        laptop.setBrand("Dell");
        products.add(laptop);
        
        Product macbook = new Product(2, "MacBook Pro", 1599.99, 8, 
                "Apple MacBook Pro with M2 chip", 
                "https://images.unsplash.com/photo-1517336714731-489689fd1ca8", 1);
        macbook.setColor("space-gray");
        macbook.setSize("14-inch");
        macbook.setBrand("Apple");
        products.add(macbook);
        
        Product smartphone = new Product(3, "iPhone 15", 999.99, 15, 
                "Latest iPhone with advanced features", 
                "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5", 1);
        smartphone.setColor("black");
        smartphone.setSize("6.1-inch");
        smartphone.setBrand("Apple");
        products.add(smartphone);
        
        Product tablet = new Product(4, "Samsung Galaxy Tab", 699.99, 12, 
                "Android tablet with high-resolution display", 
                "https://images.unsplash.com/photo-1585790050230-5dd28404ccb9", 1);
        tablet.setColor("white");
        tablet.setSize("10.5-inch");
        tablet.setBrand("Samsung");
        products.add(tablet);

        Product tshirt = new Product(5, "Cotton T-Shirt", 24.99, 50, 
                "Comfortable cotton t-shirt", 
                "https://images.unsplash.com/photo-1576566588028-4147f3842f27", 2);
        tshirt.setColor("blue");
        tshirt.setSize("M");
        tshirt.setBrand("Generic");
        products.add(tshirt);
        
        Product tshirt2 = new Product(6, "V-Neck T-Shirt", 29.99, 40, 
                "V-neck cotton t-shirt", 
                "https://images.unsplash.com/photo-1581655353564-df123a1eb820", 2);
        tshirt2.setColor("red");
        tshirt2.setSize("L");
        tshirt2.setBrand("Generic");
        products.add(tshirt2);
        
        Product jeans = new Product(7, "Slim-Fit Jeans", 59.99, 30, 
                "Slim-fit denim jeans", 
                "https://images.unsplash.com/photo-1542272604-787c3835535d", 2);
        jeans.setColor("blue");
        jeans.setSize("32");
        jeans.setBrand("Levi's");
        products.add(jeans);
        
        Product jacket = new Product(8, "Winter Jacket", 119.99, 20, 
                "Warm winter jacket with hood", 
                "https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3", 2);
        jacket.setColor("black");
        jacket.setSize("XL");
        jacket.setBrand("North Face");
        products.add(jacket);

        Product coffeemaker = new Product(9, "Coffee Maker", 89.99, 15, 
                "Programmable coffee maker", 
                "https://images.unsplash.com/photo-1520914089371-c2a13a617d7c", 3);
        coffeemaker.setColor("black");
        coffeemaker.setSize("standard");
        coffeemaker.setBrand("Cuisinart");
        products.add(coffeemaker);
        
        Product blender = new Product(10, "High-Speed Blender", 129.99, 10, 
                "High-speed blender for smoothies and more", 
                "https://images.unsplash.com/photo-1532635241-17e820acc59f", 3);
        blender.setColor("silver");
        blender.setSize("standard");
        blender.setBrand("Ninja");
        products.add(blender);
        
        return products;
    }

    @GetMapping("/{category}")
    public String getProductsByCategory(
            @PathVariable String category,
            @MatrixVariable(name = "color", required = false) String color,
            @MatrixVariable(name = "size", required = false) String size,
            @MatrixVariable(name = "brand", required = false) String brand,
            @MatrixVariable(name = "minPrice", required = false) Double minPrice,
            @MatrixVariable(name = "maxPrice", required = false) Double maxPrice,
            Model model) {
        
        List<Product> products = getSampleProducts();
        List<Category> categories = categoryService.findAll();

        int categoryId = categories.stream().filter(cat -> cat.getName().equalsIgnoreCase(category)).findFirst().map(Category::getId).orElse(0);

        List<Product> filteredProducts = products.stream()
                .filter(p -> p.getCategoryId() == categoryId)
                .collect(Collectors.toList());

        if (color != null && !color.isEmpty()) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> p.getColor() != null && p.getColor().equalsIgnoreCase(color))
                    .collect(Collectors.toList());
            model.addAttribute("selectedColor", color);
        }
        
        if (size != null && !size.isEmpty()) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> p.getSize() != null && p.getSize().equalsIgnoreCase(size))
                    .collect(Collectors.toList());
            model.addAttribute("selectedSize", size);
        }
        
        if (brand != null && !brand.isEmpty()) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> p.getBrand() != null && p.getBrand().equalsIgnoreCase(brand))
                    .collect(Collectors.toList());
            model.addAttribute("selectedBrand", brand);
        }
        
        if (minPrice != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> p.getPrice() >= minPrice)
                    .collect(Collectors.toList());
            model.addAttribute("minPrice", minPrice);
        }
        
        if (maxPrice != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> p.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
            model.addAttribute("maxPrice", maxPrice);
        }

        List<String> availableColors = products.stream()
                .filter(p -> p.getCategoryId() == categoryId)
                .map(Product::getColor)
                .distinct()
                .collect(Collectors.toList());
                
        List<String> availableSizes = products.stream()
                .filter(p -> p.getCategoryId() == categoryId)
                .map(Product::getSize)
                .distinct()
                .collect(Collectors.toList());
                
        List<String> availableBrands = products.stream()
                .filter(p -> p.getCategoryId() == categoryId)
                .map(Product::getBrand)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("products", filteredProducts);
        model.addAttribute("category", category);
        model.addAttribute("availableColors", availableColors);
        model.addAttribute("availableSizes", availableSizes);
        model.addAttribute("availableBrands", availableBrands);
        
        return "matrix_products";
    }
    

    @GetMapping("/filter/{categories}")
    public String filterMultipleCategories(
            @PathVariable String categories,
            @MatrixVariable(pathVar = "categories", name = "colors", required = false) List<String> colors,
            @MatrixVariable(pathVar = "categories", name = "sizes", required = false) List<String> sizes,
            @MatrixVariable(pathVar = "categories", name = "brands", required = false) List<String> brands,
            @MatrixVariable(pathVar = "categories", name = "priceRange", required = false) String priceRange,
            Model model) {
        
        List<Product> allProducts = getSampleProducts();
        List<Category> allCategories = categoryService.findAll();

        String[] categoryArray = categories.split(",");
        List<Integer> categoryIds = new ArrayList<>();

        for (String categoryName : categoryArray) {
            for (Category cat : allCategories) {
                if (cat.getName().equalsIgnoreCase(categoryName)) {
                    categoryIds.add(cat.getId());
                    break;
                }
            }
        }

        List<Product> filteredProducts = allProducts.stream()
                .filter(p -> categoryIds.contains(p.getCategoryId()))
                .collect(Collectors.toList());

        if (colors != null && !colors.isEmpty()) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> colors.stream()
                            .anyMatch(color -> p.getColor() != null && 
                                    p.getColor().equalsIgnoreCase(color)))
                    .collect(Collectors.toList());
            model.addAttribute("selectedColors", colors);
        }

        if (sizes != null && !sizes.isEmpty()) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> sizes.stream()
                            .anyMatch(size -> p.getSize() != null && 
                                    p.getSize().equalsIgnoreCase(size)))
                    .collect(Collectors.toList());
            model.addAttribute("selectedSizes", sizes);
        }

        if (brands != null && !brands.isEmpty()) {
            filteredProducts = filteredProducts.stream()
                    .filter(p -> brands.stream()
                            .anyMatch(brand -> p.getBrand() != null && 
                                    p.getBrand().equalsIgnoreCase(brand)))
                    .collect(Collectors.toList());
            model.addAttribute("selectedBrands", brands);
        }

        if (priceRange != null && !priceRange.isEmpty()) {
            String[] range = priceRange.split("-");
            if (range.length == 2) {
                try {
                    double min = Double.parseDouble(range[0]);
                    double max = Double.parseDouble(range[1]);
                    
                    filteredProducts = filteredProducts.stream()
                            .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                            .collect(Collectors.toList());
                    
                    model.addAttribute("priceRange", priceRange);
                } catch (NumberFormatException e) {
                }
            }
        }

        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("allCategories", allCategories);

        List<String> availableColors = allProducts.stream()
                .filter(p -> categoryIds.contains(p.getCategoryId()))
                .map(Product::getColor)
                .distinct()
                .collect(Collectors.toList());
                
        List<String> availableSizes = allProducts.stream()
                .filter(p -> categoryIds.contains(p.getCategoryId()))
                .map(Product::getSize)
                .distinct()
                .collect(Collectors.toList());
                
        List<String> availableBrands = allProducts.stream()
                .filter(p -> categoryIds.contains(p.getCategoryId()))
                .map(Product::getBrand)
                .distinct()
                .collect(Collectors.toList());
                
        model.addAttribute("availableColors", availableColors);
        model.addAttribute("availableSizes", availableSizes);
        model.addAttribute("availableBrands", availableBrands);
        
        return "matrix_products_multi";
    }
}
