package com.lease.web.admin.mapper.fee;

import com.lease.model.entity.FeeValue;
import com.lease.web.admin.dto.fee.FeeValueDto;
import com.lease.web.admin.vo.fee.FeeValueVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeeValueMapper {

    @Mapping(source = "feeKeyId", target = "feeKey.id")
    FeeValue toEntity(FeeValueDto feeValueDto);


    @Mapping(source = "feeKey.id", target = "feeKeyId")
    FeeValueVo toVo(FeeValue feeValue);

    List<FeeValueVo> toVoList(List<FeeValue> feeValueList);

}
