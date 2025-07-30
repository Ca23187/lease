package com.lease.web.admin.mapper.apartment;

import com.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.lease.web.admin.controller.projection.apartment.ApartmentItemVoProjection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApartmentItemVoProjectionMapper {
    ApartmentItemVo toVo(ApartmentItemVoProjection projection);
    List<ApartmentItemVo> toVoList(List<ApartmentItemVoProjection> projections);
}
