package com.lease.web.admin.mapper.graph;

import com.lease.model.entity.GraphInfo;
import com.lease.web.admin.dto.graph.GraphVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GraphInfoMapper {

    GraphVo toVo(GraphInfo graphInfo);

    List<GraphVo> toVoList(List<GraphInfo> graphInfoList);

    GraphInfo toEntity(GraphVo graphVo);

    List<GraphInfo> toEntityList(List<GraphVo> graphVoList);
}