package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.dto.PageDto;
import com.vicheak.springdatajpademoproject.product.web.CreateProductDto;
import com.vicheak.springdatajpademoproject.product.web.ProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductDto;
import com.vicheak.springdatajpademoproject.product.web.UpdateProductImageDto;
import com.vicheak.springdatajpademoproject.util.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{uuid}")
    public ProductDto findByUuid(@PathVariable("uuid") String uuid) {
        return productService.findByUuid(uuid);
    }

    @PostMapping
    public ResponseEntity<?> createNew(@RequestBody @Valid CreateProductDto createProductDto) {
        productService.createNew(createProductDto);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Created product resource successfully"),
                HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable("uuid") String uuid,
                                          @RequestBody @Valid UpdateProductDto updateProductDto) {
        productService.updateByUuid(uuid, updateProductDto);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Updated product resource successfully"),
                HttpStatus.OK);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updatePartialByUuid(@PathVariable("uuid") String uuid,
                                                 @RequestBody UpdateProductImageDto updateProductImageDto) {
        productService.updatePartialByUuid(uuid, updateProductImageDto);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Updated product resource successfully"),
                HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable("uuid") String uuid) {
        productService.deleteByUuid(uuid);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Deleted product resource successfully"),
                HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> findAlByPagination(@RequestParam Map<String, String> params){
        Page<Product> pages = productService.findAlByPagination(params);
        PageDto pageDto = new PageDto(pages);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("/filter")
    public List<ProductDto> findByCodeAndName(@RequestParam("name") String name,
                                              @RequestParam("code") String code){
        return productService.findProductsByCodeAndName(code, name);
    }

    @GetMapping("/count")
    public String countProductByCategory(@RequestParam("categoryId") Integer catId){
        return "Number of products = " + productService.countProductByCategory(catId);
    }

    @GetMapping("/test")
    public List<ProductDto> findByNameLikeIgnoreCase(@RequestParam("name") String name){
        return productService.findByNameLikeIgnoreCase(name);
    }

    @GetMapping("/condition/{code}")
    public List<ProductDto> findProductsByCodeGreaterThan(@PathVariable("code") String code){
        return productService.findProductsByCodeGreaterThan(code);
    }
}
