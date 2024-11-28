package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.request.RoleReq;
import NoCountry.Fineazily.model.dto.response.RoleRes;
import NoCountry.Fineazily.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "name", expression = "java(roleReq.name().toUpperCase())")
    Role toEntity(RoleReq roleReq);

    @Mapping(source= "displayName", target = "name")
    RoleRes toRoleRes(Role role);

    @Mapping(target = "name", expression = "java(roleReq.name().toUpperCase())")
    void updateRole(RoleReq roleReq, @MappingTarget Role role);

}
