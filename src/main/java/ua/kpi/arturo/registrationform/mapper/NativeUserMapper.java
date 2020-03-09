package ua.kpi.arturo.registrationform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.kpi.arturo.registrationform.dto.NativeUserDto;
import ua.kpi.arturo.registrationform.entity.User;

@Mapper(componentModel = "spring")
public interface NativeUserMapper {

    NativeUserMapper INSTANCE = Mappers.getMapper(NativeUserMapper.class);

    NativeUserDto toNativeUserDto(User user);

    @Mapping(target = "firstName", source = "firstNameNative")
    @Mapping(target = "lastName", source = "lastNameNative")
    NativeUserDto toNativeUserDtoWithNativeNames(User user);
}
