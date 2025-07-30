package com.lease.web.admin.dto.room;

import com.lease.model.entity.RoomInfo;
import com.lease.web.admin.dto.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Schema(description = "房间信息")
public class RoomSubmitDto extends RoomInfo {

    private Long apartmentId;

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "属性信息列表")
    private List<Long> attrValueIds;

    @Schema(description = "配套信息列表")
    private List<Long> facilityInfoIds;

    @Schema(description = "标签信息列表")
    private List<Long> labelInfoIds;

    @Schema(description = "支付方式列表")
    private List<Long> paymentTypeIds;

    @Schema(description = "可选租期列表")
    private List<Long> leaseTermIds;

}
