package com.ecommerce.monaco.controllers;

import com.ecommerce.monaco.beans.ProductFilter;
import com.ecommerce.monaco.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity getProducts(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String category,
                                      @RequestParam(required = false) String brand,
                                      @RequestParam(required = false) Double minPrice,
                                      @RequestParam(required = false) Double maxPrice,
                                      @RequestParam(required = false) Boolean inStock) {
        ProductFilter productFilter = new ProductFilter(name, category, brand, minPrice, maxPrice, inStock);
        return ResponseEntity.ok(productService.getProducts(productFilter));
    }


}
