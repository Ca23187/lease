package com.lease.web.app.mapper;

import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.vo.appointment.AppointmentDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(source = "apartmentInfo.id", target = "apartmentId")
    @Mapping(source = "apartmentInfo", target = "apartmentItemVo")
    @Mapping(source = "apartmentInfo.labelInfoList", target = "apartmentItemVo.labelInfoList")
    @Mapping(target = "apartmentInfo", ignore = true)
    @Mapping(target = "apartmentItemVo.feeValueList", ignore = true)
    @Mapping(target = "apartmentItemVo.facilityInfoList", ignore = true)
    @Mapping(target = "apartmentItemVo.graphInfoList", ignore = true)
    @Mapping(target = "apartmentItemVo.roomInfoList", ignore = true)
    AppointmentDetailVo toItemVo(ViewAppointment appointment);
}