package com.lease.web.admin.vo.attr;

import com.lease.model.entity.AttrKey;
import com.lease.model.entity.AttrValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AttrKeyVo extends AttrKey {

    @Schema(description = "属性value列表")
    private List<AttrValue> attrValueList;

    public AttrKeyVo(Long id, String name, List<AttrValue> attrValueList) {
        super.setId(id);
        super.setName(name);
        this.setAttrValueList(attrValueList);
    }
}
