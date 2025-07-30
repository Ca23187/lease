package com.lease.web.admin.mapper.apartment;

import com.lease.model.entity.ApartmentInfo;
import com.lease.web.admin.dto.apartment.ApartmentSubmitDto;
import com.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.lease.web.admin.vo.apartment.ApartmentInfoVo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApartmentInfoMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateFromDto(ApartmentSubmitDto dto, @MappingTarget ApartmentInfo entity);

    ApartmentDetailVo toDetailVo(ApartmentInfo apartmentInfo);

    @Mapping(source = "isRelease.code", target = "isRelease")
    ApartmentInfoVo toVo(ApartmentInfo apartmentInfo);
    List<ApartmentInfoVo> toVoList(List<ApartmentInfo> apartmentInfoList);
}
