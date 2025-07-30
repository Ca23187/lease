package com.lease.web.admin.vo.attr;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "属性值")
@Getter
@Setter
public class AttrValueVo {

    private Long id;

    private String name;

    private Long attrKeyId;
}

