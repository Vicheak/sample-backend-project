package com.vicheak.springdatajpademoproject.product;

import com.vicheak.springdatajpademoproject.product.web.CategoryDto;
import com.vicheak.springdatajpademoproject.product.web.CategoryOriginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createNew(CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryDto.name());
        if (categoryOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Category's name already existed, try a different one");
        }
        categoryRepository.save(categoryMapper.fromCategoryDto(categoryDto));
    }

    @Override
    public void updateById(Integer id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Category with id = %d not found", id))
                );
        if (categoryDto.name() != null) {
            if (!category.getName().equalsIgnoreCase(categoryDto.name())) {
                Optional<Category> categoryOptional = categoryRepository.findByName(categoryDto.name());
                if (categoryOptional.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Category's name already existed, try a different one");
                }
            }
            category.setName(categoryDto.name());
        }
        if (categoryDto.description() != null) {
            category.setDescription(categoryDto.description());
        }
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Category with id = %d not found", id))
                );
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryMapper.toCategoryDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryDto findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Category with id = %d not found", id))
                );
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryOriginDto> findAllOrigin() {
        return categoryMapper.toCategoryOriginDtoList(categoryRepository.findAll());
    }
}
