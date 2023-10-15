package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.product.web.CreateProductDto;
import com.vicheak.springdatajpademoproject.product.web.ProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductImageDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {
    /**
     * This method is used to create new product resource
     * @param createProductDto is the request from client
     */
    void createNew(CreateProductDto createProductDto);

    /**
     * This method is used to update existing product resource by uuid
     * @param uuid is the uuid of product to be updated
     * @param updateProductDto is the request from client
     */
    void updateByUuid(String uuid, UpdateProductDto updateProductDto);

    /**
     * This method is used to update product image
     * @param uuid is the uuid of product to be updated
     * @param updateProductImageDto is the request from client
     */
    void updatePartialByUuid(String uuid, UpdateProductImageDto updateProductImageDto);

    /**
     * This method is used to delete product resource by uuid
     * @param uuid is the uuid of product to be deleted
     */
    void deleteByUuid(String uuid);

    /**
     * This method is used to find all products
     * @return List<ProductDto>
     */
    List<ProductDto> findAll();

    /**
     * This method is used to find product resource by uuid
     * @param uuid is the uuid of product to be found
     * @return ProductDto
     */
    ProductDto findByUuid(String uuid);

    /**
     * This method is used to find all products with pagination
     * @param params is the request params by client
     * @return Page<ProductDto>
     */
    Page<Product> findAlByPagination(Map<String, String> params);

    List<ProductDto> findByName(String name);

    List<ProductDto> findDistinctByName(String name);

    int countProductByCategory(Integer catId);

    List<ProductDto> findProductsByCodeAndName(String code, String name);

    List<ProductDto> findByNameLikeIgnoreCase(String name);

    List<ProductDto> findProductsByCodeGreaterThan(String code);
}
