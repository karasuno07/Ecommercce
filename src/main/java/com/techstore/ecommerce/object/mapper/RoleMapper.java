package com.techstore.ecommerce.object.mapper;

import com.techstore.ecommerce.object.entity.jpa.Role;
import com.techstore.ecommerce.object.dto.request.RoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {

    Role createEntityFromRequest(RoleRequest request);

    void update(@MappingTarget Role role, RoleRequest request);
}
