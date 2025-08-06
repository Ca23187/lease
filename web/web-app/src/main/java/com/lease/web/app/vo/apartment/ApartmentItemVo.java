package com.lease.web.app.vo.apartment;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lease.model.entity.ApartmentInfo;
import com.lease.web.app.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Schema(description = "App端公寓信息")
@JsonIgnoreProperties({"facilityInfoList"})
public class ApartmentItemVo extends ApartmentInfo {

    private List<GraphVo> graphVoList;

    private BigDecimal minRent;
}
