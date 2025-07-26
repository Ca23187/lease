package com.lease.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.lease.model.enums.converter.BaseEnumToIntegerConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemUserType implements BaseEnum {

    ADMIN(0, "管理员"),
    COMMON(1, "普通用户");

    @JsonValue
    private final Integer code;

    private final String name;

    @Converter(autoApply = false)
    public static class SystemUserTypeToIntegerConverter extends BaseEnumToIntegerConverter<SystemUserType> {
        public SystemUserTypeToIntegerConverter() {
            super(SystemUserType.class);
        }
    }
}
