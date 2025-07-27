package com.lease.web.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeeValueDto {
    private Long id;
    private String name;
    private String unit;
    private Long feeKeyId;
}
