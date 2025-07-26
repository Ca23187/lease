package com.lease.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.lease.model.enums.converter.BaseEnumToIntegerConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType implements BaseEnum {

    APARTMENT(1, "公寓"),

    ROOM(2, "房间");

    @JsonValue
    private final Integer code;
    private final String name;

    @Converter(autoApply = false)
    public static class ItemTypeToIntegerConverter extends BaseEnumToIntegerConverter<ItemType> {
        public ItemTypeToIntegerConverter() {
            super(ItemType.class);
        }
    }

}
