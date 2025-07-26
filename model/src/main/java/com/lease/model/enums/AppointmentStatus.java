package com.lease.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.lease.model.enums.converter.BaseEnumToIntegerConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppointmentStatus implements BaseEnum {


    WAITING(1, "待看房"),

    CANCELED(2, "已取消"),

    VIEWED(3, "已看房");

    @JsonValue
    private final Integer code;

    private final String name;

    @Converter(autoApply = false)
    public static class AppointmentStatusToIntegerConverter extends BaseEnumToIntegerConverter<AppointmentStatus> {
        public AppointmentStatusToIntegerConverter() {
            super(AppointmentStatus.class);
        }
    }

}
