package com.lease.web.app.vo.appointment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.vo.apartment.ApartmentItemVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Schema(description = "APP端预约看房详情")
@JsonIgnoreProperties({"apartmentInfo"})
public class AppointmentDetailVo extends ViewAppointment {

    @Schema(description = "公寓基本信息")
    private ApartmentItemVo apartmentItemVo;
}
