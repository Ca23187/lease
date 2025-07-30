package com.lease.web.admin.vo.fee;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class FeeKeyVo {

    @Schema(description = "主键 ID")
    private Long id;

    @Schema(description = "杂费名称")
    private String name;

    @JsonProperty("feeValueList")
    @Schema(description = "杂费value列表")
    private List<FeeValueVo> feeValueVoList;
}
