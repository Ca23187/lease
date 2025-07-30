package com.lease.web.admin.mapper.fee;

import com.lease.model.entity.FeeValue;
import com.lease.web.admin.vo.fee.FeeValueVoWithName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeeValueWithNameMapper {

    @Mapping(source = "feeKey.id", target = "feeKeyId")
    @Mapping(source = "feeKey.name", target = "feeKeyName")
    FeeValueVoWithName toVoWithName(FeeValue feeValue);

    List<FeeValueVoWithName> toVoListWithName(List<FeeValue> feeValueList);

}