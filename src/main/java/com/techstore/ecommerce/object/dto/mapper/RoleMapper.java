package com.techstore.ecommerce.object.dto.mapper;

import com.techstore.ecommerce.object.dto.request.RoleRequest;
import com.techstore.ecommerce.object.dto.response.RoleResponse;
import com.techstore.ecommerce.object.entity.jpa.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleResponse toResponseModel(Role role);

    Role createEntityFromRequest(RoleRequest request);

    void update(@MappingTarget Role role, RoleRequest request);
}
