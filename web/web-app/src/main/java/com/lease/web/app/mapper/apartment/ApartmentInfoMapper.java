package com.lease.web.app.mapper.apartment;

import com.lease.model.entity.ApartmentInfo;
import com.lease.web.app.vo.apartment.ApartmentDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApartmentInfoMapper {
    @Mapping(target = "feeValueList", ignore = true)
    @Mapping(target = "graphInfoList", ignore = true)
    @Mapping(target = "roomInfoList", ignore = true)
    ApartmentDetailVo toDetailVo(ApartmentInfo apartmentInfo);
}