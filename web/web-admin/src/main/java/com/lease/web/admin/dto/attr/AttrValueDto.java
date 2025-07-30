package com.lease.web.admin.dto.attr;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttrValueDto {
    private Long id;
    private String name;
    private Long attrKeyId;
}
