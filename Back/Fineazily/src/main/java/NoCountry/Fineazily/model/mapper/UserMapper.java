package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.UserDto;
import NoCountry.Fineazily.model.entity.User;

import java.util.List;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto dto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);
}
