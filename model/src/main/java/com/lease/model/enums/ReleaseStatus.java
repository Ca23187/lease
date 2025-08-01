package com.lease.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.lease.model.enums.converter.BaseEnumToIntegerConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReleaseStatus implements BaseEnum {

    RELEASED(1, "已发布"),
    NOT_RELEASED(0, "未发布");

    @JsonValue
    private final Integer code;

    private final String name;

    @Converter(autoApply = false)
    public static class ReleaseStatusToIntegerConverter extends BaseEnumToIntegerConverter<ReleaseStatus> {
        public ReleaseStatusToIntegerConverter() {
            super(ReleaseStatus.class);
        }
    }
}
