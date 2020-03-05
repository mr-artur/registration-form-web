package ua.kpi.arturo.registrationform.controller;

import org.springframework.context.i18n.LocaleContextHolder;
import ua.kpi.arturo.registrationform.dto.NativeUserDto;
import ua.kpi.arturo.registrationform.entity.User;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class NativeUserBuilder {

    public List<NativeUserDto> localizeUsers(List<User> users) {
        Locale locale = LocaleContextHolder.getLocale();
        return users.stream()
            .map(user -> createNativeUser(user, locale))
            .collect(Collectors.toList());
    }

    private NativeUserDto createNativeUser(User user, Locale locale) {
        NativeUserDto nativeUser = buildStableFields(user);
        setLocalizedFields(nativeUser, user, locale);
        return nativeUser;
    }

    private void setLocalizedFields(NativeUserDto nativeUser, User user, Locale locale) {
        if (locale.equals(Locale.ENGLISH)) {
            nativeUser.setFirstName(user.getFirstName());
            nativeUser.setLastName(user.getLastName());
        } else {
            nativeUser.setFirstName(user.getFirstNameNative());
            nativeUser.setLastName(user.getLastNameNative());
        }
    }

    private NativeUserDto buildStableFields(User user) {
        return NativeUserDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    }
}
