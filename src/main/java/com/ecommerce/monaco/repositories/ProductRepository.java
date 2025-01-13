package com.ecommerce.monaco.repositories;


import com.ecommerce.monaco.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
