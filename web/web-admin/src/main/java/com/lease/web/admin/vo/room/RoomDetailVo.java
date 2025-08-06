package com.lease.web.admin.vo.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lease.model.entity.ApartmentInfo;
import com.lease.model.entity.RoomInfo;
import com.lease.web.admin.vo.graph.GraphVo;
import com.lease.web.admin.vo.attr.AttrValueVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Schema(description = "房间信息")
@Getter
@Setter
public class RoomDetailVo extends RoomInfo {

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "属性信息列表")
    private List<AttrValueVo> attrValueVoList;

    @JsonIgnoreProperties({"facilityInfoList", "labelInfoList"})
    private ApartmentInfo apartmentInfo;

}
