package com.lease.web.app.mapper.room;

import com.lease.model.entity.RoomInfo;
import com.lease.web.app.vo.room.RoomDetailVo;
import com.lease.web.app.vo.room.RoomItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomInfoMapper {

    @Mapping(target = "labelInfoList", ignore = true)
    @Mapping(target = "apartmentInfo.facilityInfoList", ignore = true)
    @Mapping(target = "apartmentInfo.labelInfoList", ignore = true)
    @Mapping(target = "apartmentInfo.feeValueList", ignore = true)
    @Mapping(target = "apartmentInfo.graphInfoList", ignore = true)
    @Mapping(target = "apartmentInfo.roomInfoList", ignore = true)
    RoomItemVo toVo(RoomInfo roomInfo);

    @Mapping(source = "apartmentInfo.id", target = "apartmentId")
    @Mapping(source = "apartmentInfo", target = "apartmentItemVo")
    @Mapping(source = "apartmentInfo.labelInfoList", target = "apartmentItemVo.labelInfoList")
    @Mapping(target = "attrValueList", ignore = true)
    @Mapping(target = "graphInfoList", ignore = true)
    @Mapping(target = "apartmentInfo", ignore = true)
    @Mapping(target = "apartmentItemVo.feeValueList", ignore = true)
    @Mapping(target = "apartmentItemVo.facilityInfoList", ignore = true)
    @Mapping(target = "apartmentItemVo.graphInfoList", ignore = true)
    @Mapping(target = "apartmentItemVo.roomInfoList", ignore = true)
    RoomDetailVo toDetailVo(RoomInfo roomInfo);
}