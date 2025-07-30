package com.lease.web.admin.vo.apartment;

import com.lease.model.entity.ApartmentInfo;
import com.lease.web.admin.dto.graph.GraphVo;
import com.lease.web.admin.vo.fee.FeeValueVoWithName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "公寓信息")
@Getter
@Setter
public class ApartmentDetailVo extends ApartmentInfo {

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "杂费列表")
    private List<FeeValueVoWithName> feeValueVoList;

}
