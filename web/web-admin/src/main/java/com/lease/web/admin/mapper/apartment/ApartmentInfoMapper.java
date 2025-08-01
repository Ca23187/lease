package com.lease.web.admin.mapper.apartment;

import com.lease.model.entity.ApartmentInfo;
import com.lease.web.admin.dto.apartment.ApartmentSubmitDto;
import com.lease.web.admin.vo.apartment.ApartmentDetailVo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ApartmentInfoMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "roomInfoList", ignore = true)
    void updateFromDto(ApartmentSubmitDto dto, @MappingTarget ApartmentInfo entity);

    @Mapping(target = "feeValueList", ignore = true)
    @Mapping(target = "graphInfoList", ignore = true)
    @Mapping(target = "roomInfoList", ignore = true)
    ApartmentDetailVo toDetailVo(ApartmentInfo apartmentInfo);
}
