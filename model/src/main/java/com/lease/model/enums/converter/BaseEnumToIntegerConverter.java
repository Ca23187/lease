package com.lease.model.enums.converter;

import com.lease.model.enums.BaseEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class BaseEnumToIntegerConverter<E extends Enum<E> & BaseEnum> implements AttributeConverter<E, Integer> {

    private final Class<E> enumClass;

    public BaseEnumToIntegerConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (E e : enumClass.getEnumConstants()) {
            if (e.getCode().equals(dbData)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown code for enum " + enumClass.getSimpleName() + ": " + dbData);
    }
}

