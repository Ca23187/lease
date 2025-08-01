package com.lease.web.admin.mapper.room;

import com.lease.model.entity.RoomInfo;
import com.lease.web.admin.dto.room.RoomSubmitDto;
import com.lease.web.admin.vo.room.RoomDetailVo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoomInfoMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "apartmentId", target = "apartmentInfo.id")
    void updateFromDto(RoomSubmitDto dto, @MappingTarget RoomInfo entity);

    @Mapping(source = "apartmentInfo.id", target = "apartmentId")
    @Mapping(target = "apartmentInfo.facilityInfoList", ignore = true)
    @Mapping(target = "apartmentInfo.labelInfoList", ignore = true)
    @Mapping(target = "apartmentInfo.feeValueList", ignore = true)
    @Mapping(target = "apartmentInfo.graphInfoList", ignore = true)
    @Mapping(target = "apartmentInfo.roomInfoList", ignore = true)
    @Mapping(target = "attrValueList", ignore = true)
    @Mapping(target = "graphInfoList", ignore = true)
    RoomDetailVo toDetailVo(RoomInfo RoomInfo);
}