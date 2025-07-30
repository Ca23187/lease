package com.lease.web.admin.vo.room;

import com.lease.model.entity.RoomInfo;
import com.lease.web.admin.dto.graph.GraphVo;
import com.lease.web.admin.vo.attr.AttrValueVoWithName;
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
    private List<AttrValueVoWithName> attrValueVoList;

}
