package com.lease.web.admin.vo.fee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "杂费值")
public class FeeValueVo {
    private Long id;

    private String name;

    private String unit;

    private String feeKeyId;

}

