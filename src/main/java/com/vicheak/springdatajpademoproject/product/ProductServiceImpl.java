package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.exception.ResourceException;
import com.vicheak.springdatajpademoproject.product.web.CreateProductDto;
import com.vicheak.springdatajpademoproject.product.web.ProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductImageDto;
import com.vicheak.springdatajpademoproject.util.PageUtil;
import com.vicheak.springdatajpademoproject.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public void createNew(CreateProductDto createProductDto) {
        Product product = productMapper.fromCreateProductDto(createProductDto);
        product.setUuid(UUID.randomUUID().toString());
        product.setCode("PRO-" + RandomUtil.generateCode());
        productRepository.save(product);
    }

    @Override
    public void updateByUuid(String uuid, UpdateProductDto updateProductDto) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Product with uuid = %s not found", uuid))
                );
        //System.out.println("Before mapping :");
        //System.out.println(product);

        productMapper.fromUpdateProductDto(product, updateProductDto);

        if (updateProductDto.categoryId() != null) {
            product.setCategory(Category.builder()
                    .id(updateProductDto.categoryId())
                    .build());
        }

        //System.out.println(product);

        productRepository.save(product);
    }

    @Override
    public void updatePartialByUuid(String uuid, UpdateProductImageDto updateProductImageDto) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Product with uuid = %s not found", uuid))
                );
        productMapper.fromUpdateProductImageDto(product, updateProductImageDto);
        productRepository.save(product);
    }

    @Override
    public void deleteByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Product with uuid = %s not found", uuid))
                );
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return productMapper.toProductDtoList(productList);
    }

    @Override
    public ProductDto findByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Product with uuid = %s not found", uuid))
                );
        return productMapper.toProductDto(product);
    }

    @Override
    public Page<Product> findAlByPagination(Map<String, String> params) {
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<ProductDto> findByName(String name) {
        return productMapper.toProductDtoList(productRepository.findByName(name));
    }

    @Override
    public List<ProductDto> findDistinctByName(String name) {
        return productMapper.toProductDtoList(productRepository.findDistinctByName(name));
    }

    @Override
    public int countProductByCategory(Integer catId) {
        Category category = Category.builder()
                .id(catId)
                .build();
        return productRepository.countProductByCategory(category);
    }

    @Override
    public List<ProductDto> findProductsByCodeAndName(String code, String name) {
        return productMapper.toProductDtoList(productRepository.findProductsByCodeAndName(code, name));
    }

    @Override
    public List<ProductDto> findByNameLikeIgnoreCase(String name) {
        return productMapper.toProductDtoList(productRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public List<ProductDto> findProductsByCodeGreaterThan(String code) {
        return productMapper.toProductDtoList(productRepository.findProductsByCodeGreaterThan(code));
    }
}
