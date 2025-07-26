package com.lease.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.lease.model.enums.converter.BaseEnumToIntegerConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LeaseSourceType implements BaseEnum {

    NEW(1, "新签"),
    RENEW(2, "续约");

    @JsonValue
    private final Integer code;

    private final String name;

    @Converter(autoApply = false)
    public static class LeaseSourceTypeToIntegerConverter extends BaseEnumToIntegerConverter<LeaseSourceType> {
        public LeaseSourceTypeToIntegerConverter() {
            super(LeaseSourceType.class);
        }
    }
}
