package com.lease.web.app.vo.attr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class AttrValueVo implements Serializable {
    private Long id;
    private String name;
    private Long attrKeyId;
    private String attrKeyName;
}
