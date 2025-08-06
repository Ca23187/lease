package com.lease.web.app.vo.fee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class FeeValueVo implements Serializable {
    private Long id;
    private String name;
    private String unit;
    private Long feeKeyId;
    private String feeKeyName;
}
