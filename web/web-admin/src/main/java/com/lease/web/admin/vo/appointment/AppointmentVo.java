package com.lease.web.admin.vo.appointment;

import com.lease.model.entity.ViewAppointment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "预约看房信息")
public class AppointmentVo extends ViewAppointment {

    @Schema(description = "预约公寓信息id")
    private Long apartmentId;

}
