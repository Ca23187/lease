package com.lease.web.admin.mapper.attr;

import com.lease.model.entity.AttrKey;
import com.lease.web.admin.vo.attr.AttrKeyVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AttrValueMapper.class)
public interface AttrKeyMapper {

    @Mapping(source = "attrValueList", target = "attrValueVoList")
    AttrKeyVo toVo(AttrKey attrKey);

    List<AttrKeyVo> toVoList(List<AttrKey> attrKeyList);
}
