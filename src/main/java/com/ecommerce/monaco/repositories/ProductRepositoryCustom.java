package com.ecommerce.monaco.repositories;


import com.ecommerce.monaco.beans.ProductFilter;
import com.ecommerce.monaco.entities.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findProductByFilters(ProductFilter productFilter);

}
