package com.lease.web.admin.mapper.attr;

import com.lease.model.entity.AttrValue;
import com.lease.web.admin.dto.attr.AttrValueDto;
import com.lease.web.admin.vo.attr.AttrValueVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttrValueMapper {

    @Mapping(source = "attrKeyId", target = "attrKey.id")
    AttrValue toEntity(AttrValueDto attrValueDto);

    @Mapping(source = "attrKey.id", target = "attrKeyId")
    AttrValueVo toVo(AttrValue attrValue);

    List<AttrValueVo> toVoList(List<AttrValueVo> attrValueVoList);

}
