package com.lease.web.admin.mapper.room;

import com.lease.model.entity.RoomInfo;
import com.lease.web.admin.dto.room.RoomSubmitDto;
import com.lease.web.admin.vo.room.RoomDetailVo;
import com.lease.web.admin.vo.room.RoomInfoVo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomInfoMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "apartmentId", target = "apartmentInfo.id")
    void updateFromDto(RoomSubmitDto dto, @MappingTarget RoomInfo entity);

    RoomDetailVo toDetailVo(RoomInfo RoomInfo);

    RoomInfoVo toVo(RoomInfo RoomInfo);
    List<RoomInfoVo> toVoList(List<RoomInfo> RoomInfoList);
}