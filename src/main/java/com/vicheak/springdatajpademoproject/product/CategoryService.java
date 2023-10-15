package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.product.web.CategoryDto;
import com.vicheak.springdatajpademoproject.product.web.CategoryOriginDto;

import java.util.List;

public interface CategoryService {
    /**
     * This method is used to create new category resource
     * @param categoryDto is the request data from client
     */
    void createNew(CategoryDto categoryDto);

    /**
     * This method is used to update existing category resource by category's ID
     * @param id is the ID of category to update
     * @param categoryDto is the request data from client
     */
    void updateById(Integer id, CategoryDto categoryDto);

    /**
     * This method is used to delete category resource by category's ID
     * @param id is the ID of category to delete
     */
    void deleteById(Integer id);

    /**
     * This method is used to find all category resources from database
     * @return List<CategoryDto>
     */
    List<CategoryDto> findAll();

    /**
     * This method is used to find category resource by id
     * @param id is the ID of category to find
     * @return CategoryDto
     */
    CategoryDto findById(Integer id);

    /**
     * This method is used to find category origin resources from database
     * @return List<CategoryOriginDto>
     */
    List<CategoryOriginDto> findAllOrigin();
}
