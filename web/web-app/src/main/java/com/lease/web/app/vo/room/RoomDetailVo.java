package com.lease.web.app.vo.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lease.model.entity.*;
import com.lease.web.app.vo.apartment.ApartmentItemVo;
import com.lease.web.app.vo.attr.AttrValueVo;
import com.lease.web.app.vo.fee.FeeValueVo;
import com.lease.web.app.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Schema(description = "APP房间详情")
@JsonIgnoreProperties({"apartmentInfo"})
public class RoomDetailVo extends RoomInfo {

    @Schema(description = "所属公寓信息")
    private ApartmentItemVo apartmentItemVo;

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "属性信息列表")
    private List<AttrValueVo> attrValueVoList;

    @Schema(description = "杂费列表")
    private List<FeeValueVo> feeValueVoList;


}
