package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.product.web.CategoryDto;
import com.vicheak.springdatajpademoproject.product.web.CategoryOriginDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCategoryDto(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtoList(List<Category> categories);

    CategoryOriginDto toCategoryOriginDto(Category category);

    List<CategoryOriginDto> toCategoryOriginDtoList(List<Category> categories);
}
