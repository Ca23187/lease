package com.lease.web.admin.vo.attr;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AttrKeyVo {

    @Schema(description = "属性key ID")
    private Long id;

    @Schema(description = "属性key名称")
    private String name;

    @Schema(description = "属性value列表")
    @JsonProperty("attrValueList")
    private List<AttrValueVo> attrValueVoList;

}