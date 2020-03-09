package ua.kpi.arturo.registrationform.controller;

import org.springframework.context.i18n.LocaleContextHolder;
import ua.kpi.arturo.registrationform.dto.NativeUserDto;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.mapper.NativeUserMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class NativeUserBuilder {

    private NativeUserMapper nativeUserMapper = NativeUserMapper.INSTANCE;

    public List<NativeUserDto> localizeUsers(List<User> users) {
        Locale locale = LocaleContextHolder.getLocale();

        return users.stream()
            .map(user -> localizeUser(user, locale))
            .collect(Collectors.toList());
    }

    private NativeUserDto localizeUser(User user, Locale locale) {
        if (locale.equals(Locale.ENGLISH)) {
            return nativeUserMapper.toNativeUserDto(user);
        }
        return nativeUserMapper.toNativeUserDtoWithNativeNames(user);
    }
}
