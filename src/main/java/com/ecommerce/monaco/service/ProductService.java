package com.ecommerce.monaco.service;

import com.ecommerce.monaco.beans.ProductFilter;
import com.ecommerce.monaco.entities.Product;
import com.ecommerce.monaco.exceptions.NoDataFoundException;
import com.ecommerce.monaco.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    /**
     * Get products based on the filters
     * @param productFilter
     * @return
     */
    public List<Product> getProducts(ProductFilter productFilter){
        List<Product> products = productRepository.findProductByFilters(productFilter);
        if(products.isEmpty()){
            logger.warn("No products found");
            throw new NoDataFoundException("No product found with the given filters");
        }
        logger.info("Found {} products", products.size());
        return products;
    }
}
