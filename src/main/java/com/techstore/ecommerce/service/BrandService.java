package com.techstore.ecommerce.service;

import com.techstore.ecommerce.object.exception.ResourceNotFoundException;
import com.techstore.ecommerce.object.dto.request.BrandRequest;
import com.techstore.ecommerce.object.entity.jpa.Brand;
import com.techstore.ecommerce.object.dto.mapper.BrandMapper;
import com.techstore.ecommerce.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepo;
  private final BrandMapper brandMapper;
  private final CloudinaryService cloudinaryService;

  public List<Brand> findAllBrands(){
    return brandRepo.findAll();
  }

  public Brand findBrandById(long id) {
    return brandRepo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Not found any brand with id " + id));
  }

  public Brand createBrand(BrandRequest request) {
    Brand brand = brandMapper.createEntityFromRequest(request);
    existingBrand(brand);
    if (!ObjectUtils.isEmpty(request.getImageFile())) {
      String image = cloudinaryService.uploadImage(null, request.getImageFile());
      if (image != null) brand.setImage(image);
    }
    return brandRepo.save(brand);
  }

  public Brand updateBrand(long id, BrandRequest request) {
    Brand brand = findBrandById(id);
    brandMapper.update(brand, request);
    existingBrand(brand);

    if (!ObjectUtils.isEmpty(request.getImageFile())) {
      String image = cloudinaryService.uploadImage(brand.getImage(), request.getImageFile());
      if (image != null) brand.setImage(image);
    }

    return brandRepo.save(brand);
  }

  public void deleteBrand(long id) {
    Brand brand = findBrandById(id);
    brandRepo.delete(brand);
  }

  protected void existingBrand(Brand brand){
    boolean existing = brandRepo.existsByName(brand.getName());
    if(existing){
      throw new EntityExistsException("Brand name " + brand.getName() + " already exists");
    }
  }
}
