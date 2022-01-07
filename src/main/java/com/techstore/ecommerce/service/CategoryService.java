package com.techstore.ecommerce.service;

import com.techstore.ecommerce.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.request.CategoryRequest;
import com.techstore.ecommerce.object.entity.jpa.Category;
import com.techstore.ecommerce.object.mapper.CategoryMapper;
import com.techstore.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;

    public List<Category> findAllCategories(){
        return categoryRepo.findAll();
    }

    public Category findCategoryById(long id) {
        return categoryRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found any category with id " + id));
    }

    public Category createCategory(CategoryRequest request) {
        Category category = categoryMapper.createEntityFromRequest(request);
        existingCategory(category);
        return categoryRepo.save(category);
    }

    public Category updateCategory(long id, CategoryRequest request) {
        Category category = findCategoryById(id);
        categoryMapper.update(category, request);
        return categoryRepo.save(category);
    }

    public void deleteCategory(long id) {
        Category category = findCategoryById(id);
        categoryRepo.delete(category);
    }

    protected void existingCategory(Category category){
        boolean existing = categoryRepo.existsByName(category.getName());
        if(existing){
            throw new EntityExistsException("Category name " + category.getName() + " already exists");
        }
    }
}
