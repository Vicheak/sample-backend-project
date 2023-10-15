package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.product.web.CreateProductDto;
import com.vicheak.springdatajpademoproject.product.web.ProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductImageDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "categoryId", target = "category.id")
    Product fromCreateProductDto(CreateProductDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateProductDto(@MappingTarget Product product, UpdateProductDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateProductImageDto(@MappingTarget Product product, UpdateProductImageDto dto);

    @Mapping(source = "category", target = "category")
    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtoList(List<Product> products);
}
