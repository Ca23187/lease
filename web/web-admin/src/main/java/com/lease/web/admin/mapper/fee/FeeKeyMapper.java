package com.lease.web.admin.mapper.fee;

import com.lease.model.entity.FeeKey;
import com.lease.web.admin.vo.fee.FeeKeyVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = FeeValueMapper.class)
public interface FeeKeyMapper {

    @Mapping(source = "feeValueList", target = "feeValueVoList")
    FeeKeyVo toVo(FeeKey feeKey);

    List<FeeKeyVo> toVoList(List<FeeKey> feeKeyList);
}