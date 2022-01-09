package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.dto.request.SupplierRequest;
import com.techstore.ecommerce.object.dto.response.SupplierResponse;
import com.techstore.ecommerce.object.entity.jpa.Receipt;
import com.techstore.ecommerce.object.entity.jpa.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-09T20:10:05+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierResponse toResponseModel(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierResponse supplierResponse = new SupplierResponse();

        supplierResponse.setId( supplier.getId() );
        supplierResponse.setName( supplier.getName() );
        supplierResponse.setAddress( supplier.getAddress() );
        supplierResponse.setPhoneNumber( supplier.getPhoneNumber() );
        supplierResponse.setEmail( supplier.getEmail() );
        List<Receipt> list = supplier.getReceipts();
        if ( list != null ) {
            supplierResponse.setReceipts( new ArrayList<Receipt>( list ) );
        }

        return supplierResponse;
    }

    @Override
    public Supplier createEntityFromRequest(SupplierRequest request) {
        if ( request == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setName( request.getName() );
        supplier.setPhoneNumber( request.getPhoneNumber() );
        supplier.setEmail( request.getEmail() );

        getAddressFromModel( supplier, request );

        return supplier;
    }

    @Override
    public void update(Supplier supplier, SupplierRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            supplier.setName( request.getName() );
        }
        if ( request.getPhoneNumber() != null ) {
            supplier.setPhoneNumber( request.getPhoneNumber() );
        }
        if ( request.getEmail() != null ) {
            supplier.setEmail( request.getEmail() );
        }

        getAddressFromModel( supplier, request );
    }
}
