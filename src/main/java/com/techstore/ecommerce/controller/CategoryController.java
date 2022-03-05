package com.techstore.ecommerce.controller;

import com.techstore.ecommerce.object.dto.mapper.CategoryMapper;
import com.techstore.ecommerce.object.dto.request.CategoryRequest;
import com.techstore.ecommerce.object.dto.response.CategoryResponse;
import com.techstore.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    //    @PreAuthorize("hasAuthority('CATEGORY_READ')")
    @GetMapping
    ResponseEntity<?> getAllCategory() {
        List<CategoryResponse> response = service.findAllCategories().stream()
                                                 .map(mapper::toResponseModel)
                                                 .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    //    @PreAuthorize("hasAuthority('CATEGORY_READ')")
    @GetMapping("/{categoryId}")
    ResponseEntity<?> getCategoryById(@PathVariable int categoryId) {
        CategoryResponse response = mapper.toResponseModel(service.findCategoryById(categoryId));
        return ResponseEntity.ok(response);
    }

    //    @PreAuthorize("hasAuthority('CATEGORY_CREATE')")
    @PostMapping
    ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequest request) {
        CategoryResponse response = mapper.toResponseModel(service.createCategory(request));
        return ResponseEntity.ok(response);
    }

    //    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    @PutMapping("/{categoryId}")
    ResponseEntity<?> updateCategory(
            @PathVariable int categoryId, @RequestBody @Valid CategoryRequest request) {
        CategoryResponse response = mapper.toResponseModel(service.updateCategory(categoryId, request));
        return ResponseEntity.ok(response);
    }

    //    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    @DeleteMapping("/{categoryId}")
    ResponseEntity<?> deleteCategory(@PathVariable long categoryId) {
        service.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}
