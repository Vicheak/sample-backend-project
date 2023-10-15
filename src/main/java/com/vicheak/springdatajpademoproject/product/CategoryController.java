package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.product.web.CategoryDto;
import com.vicheak.springdatajpademoproject.product.web.CategoryOriginDto;
import com.vicheak.springdatajpademoproject.util.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id);
    }

    @GetMapping("/origin")
    public List<CategoryOriginDto> findAllOrigin(){
        return categoryService.findAllOrigin();
    }

    @PostMapping
    public ResponseEntity<?> createNew(@RequestBody @Valid CategoryDto categoryDto) {
        categoryService.createNew(categoryDto);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Created category resource successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody @Valid CategoryDto categoryDto) {
        categoryService.updateById(id, categoryDto);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Updated category resource successfully"),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartialById(@PathVariable("id") Integer id,
                                               @RequestBody CategoryDto categoryDto) {
        categoryService.updateById(id, categoryDto);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Updated category resource successfully"),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(
                ResponseSuccess.responseMapping("Deleted category resource successfully"),
                HttpStatus.NO_CONTENT);
    }

}
