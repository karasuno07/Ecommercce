package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.constant.SuccessMessage;
import com.techstore.ecommerce.object.dto.request.CategoryRequest;
import com.techstore.ecommerce.object.dto.response.CategoryResponse;
import com.techstore.ecommerce.object.mapper.CategoryMapper;
import com.techstore.ecommerce.object.wrapper.AbstractResponse;
import com.techstore.ecommerce.object.wrapper.SuccessResponse;
import com.techstore.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping
    AbstractResponse getAllCategory() {
        List<CategoryResponse> response =
                service.findAllCategorys().stream()
                       .map(mapper::toResponseModel)
                       .collect(Collectors.toList());

        return new SuccessResponse<>(response, SuccessMessage.FIND_ALL_CATEGORIES.getMessage());
    }

    @GetMapping("/{categoryId}")
    AbstractResponse getCategoryById(@PathVariable int categoryId) {
        CategoryResponse response = mapper.toResponseModel(service.findCategoryById(categoryId));
        return new SuccessResponse<>(
                response, SuccessMessage.FIND_CATEGORY_BY_ID.getMessage() + categoryId);
    }

    @PostMapping
    AbstractResponse createCategory(@RequestBody @Valid CategoryRequest request) {
        CategoryResponse response = mapper.toResponseModel(service.createCategory(request));
        return new SuccessResponse<>(
                response, HttpStatus.CREATED.value(), SuccessMessage.CREATE_CATEGORY.getMessage());
    }

    @PutMapping("/{categoryId}")
    AbstractResponse updateCategory(
            @PathVariable int categoryId, @RequestBody @Valid CategoryRequest request) {
        CategoryResponse response = mapper.toResponseModel(service.updateCategory(categoryId, request));
        return new SuccessResponse<>(response, SuccessMessage.UPDATE_CATEGORY.getMessage());
    }

    @DeleteMapping("/{categoryId}")
    AbstractResponse deleteCategory(@PathVariable long categoryId) {
        service.deleteCategory(categoryId);
        return new SuccessResponse<>(null, SuccessMessage.DELETE_CATEGORY.getMessage());
    }
}
