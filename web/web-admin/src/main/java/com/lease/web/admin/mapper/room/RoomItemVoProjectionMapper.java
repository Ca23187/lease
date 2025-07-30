package com.lease.web.admin.mapper.room;

import com.lease.web.admin.controller.projection.room.RoomItemVoProjection;
import com.lease.web.admin.vo.room.RoomItemVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomItemVoProjectionMapper {
    RoomItemVo toVo(RoomItemVoProjection projection);
    List<RoomItemVo> toVoList(List<RoomItemVoProjection> projections);
}