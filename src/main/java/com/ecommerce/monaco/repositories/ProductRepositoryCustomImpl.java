package com.ecommerce.monaco.repositories;

import com.ecommerce.monaco.beans.ProductFilter;
import com.ecommerce.monaco.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Product> findProductByFilters(ProductFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        Predicate predicate = cb.conjunction();
        if (filter.getName() != null) {
            predicate = cb.and(predicate, cb.equal(cb.lower(product.get("name")), filter.getName().toLowerCase()));
        }
        if (filter.getCategory() != null) {
            predicate = cb.and(predicate, cb.equal(cb.lower(product.get("category")), filter.getCategory().toLowerCase()));
        }
        if (filter.getBrand() != null) {
            predicate = cb.and(predicate, cb.equal(cb.lower(product.get("brand")), filter.getBrand().toLowerCase()));
        }
        if (filter.getMinPrice() != null) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(product.get("price"), filter.getMinPrice()));
        }
        if (filter.getMaxPrice() != null) {
            predicate = cb.and(predicate, cb.lessThanOrEqualTo(product.get("price"), filter.getMaxPrice()));
        }
        if (filter.getInStock() != null) {
            predicate = cb.and(predicate, cb.equal(product.get("inStock"), filter.getInStock()));
        }
        query.where(predicate);
        return entityManager.createQuery(query).getResultList();
    }
}
