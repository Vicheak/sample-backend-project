package com.vicheak.springdatajpademoproject.dto;

import com.vicheak.springdatajpademoproject.product.Product;
import com.vicheak.springdatajpademoproject.product.ProductMapper;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    private List<?> list;
    private PaginationDto pagination;

    public PageDto(Page<Product> page) {
        this.list = ProductMapper.INSTANCE.toProductDtoList(page.getContent());
        this.pagination = PaginationDto.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}
