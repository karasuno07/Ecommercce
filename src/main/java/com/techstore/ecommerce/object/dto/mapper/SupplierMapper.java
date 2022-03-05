package com.techstore.ecommerce.object.dto.mapper;

import com.techstore.ecommerce.object.dto.request.SupplierRequest;
import com.techstore.ecommerce.object.dto.response.SupplierResponse;
import com.techstore.ecommerce.object.entity.jpa.Supplier;
import com.techstore.ecommerce.object.model.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {

    SupplierResponse toResponseModel(Supplier supplier);

    @Mapping(target = "address", ignore = true)
    Supplier createEntityFromRequest(SupplierRequest request);

    @Mapping(target = "address", ignore = true)
    void update(@MappingTarget Supplier supplier, SupplierRequest request);

    @AfterMapping
    default void getAddressFromModel(@MappingTarget Supplier supplier, SupplierRequest request) {
        Address address = request.getAddress();
        String recipientAddress = String.format("%s, %s, %s, %s",
                address.getStreet(), address.getWard(),
                address.getDistrict(), address.getCity());
        supplier.setAddress(recipientAddress);
    }
}
