package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.BrandRequest;
import com.techstore.ecommerce.object.dto.response.BrandResponse;
import com.techstore.ecommerce.object.entity.jpa.Brand;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-09T16:41:35+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandResponse toResponseModel(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandResponse brandResponse = new BrandResponse();

        brandResponse.setId( brand.getId() );
        brandResponse.setName( brand.getName() );
        brandResponse.setSlug( brand.getSlug() );

        return brandResponse;
    }

    @Override
    public Brand createEntityFromRequest(BrandRequest request) {
        if ( request == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setName( request.getName() );

        generateSlug( brand, request );

        return brand;
    }

    @Override
    public void update(Brand brand, BrandRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            brand.setName( request.getName() );
        }

        generateSlug( brand, request );
    }
}
