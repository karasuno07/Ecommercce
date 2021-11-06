package com.techstore.ecommercce.object.mapper;

import com.techstore.ecommercce.object.entity.Role;
import com.techstore.ecommercce.object.request.RoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {

    Role createEntityFromRequest(RoleRequest request);

    void update(@MappingTarget Role role, RoleRequest request);
}
