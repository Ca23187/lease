package com.lease.web.app.vo.apartment;

import com.lease.model.entity.ApartmentInfo;
import com.lease.web.app.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Setter
@Getter
@Schema(description = "APP端公寓信息详情")
public class ApartmentDetailVo extends ApartmentInfo {

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "租金最小值")
    private BigDecimal minRent;
}
