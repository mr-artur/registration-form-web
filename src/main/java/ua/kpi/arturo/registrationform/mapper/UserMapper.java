package ua.kpi.arturo.registrationform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.kpi.arturo.registrationform.dto.UserDto;
import ua.kpi.arturo.registrationform.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);
}
