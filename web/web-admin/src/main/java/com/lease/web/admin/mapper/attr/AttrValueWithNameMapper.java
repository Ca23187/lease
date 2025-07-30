package com.lease.web.admin.mapper.attr;

import com.lease.model.entity.AttrValue;
import com.lease.web.admin.vo.attr.AttrValueVoWithName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttrValueWithNameMapper {

    @Mapping(source = "attrKey.id", target = "attrKeyId")
    @Mapping(source = "attrKey.name", target = "attrKeyName")
    AttrValueVoWithName toVoWithName(AttrValue attrValue);

    List<AttrValueVoWithName> toVoListWithName(List<AttrValue> attrValueList);

}