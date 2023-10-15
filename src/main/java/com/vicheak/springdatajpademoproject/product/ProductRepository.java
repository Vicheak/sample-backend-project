package com.vicheak.springdatajpademoproject.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByUuid(String uuid);

    //derived query methods
    List<Product> findByName(String name);

    List<Product> findDistinctByName(String name);

    int countProductByCategory(Category category);

    //multiple parameters
    List<Product> findProductsByCodeAndName(String code, String name);

    //equality conditions
    List<Product> findByNameLikeIgnoreCase(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findProductsByCodeGreaterThan(String code);
}
