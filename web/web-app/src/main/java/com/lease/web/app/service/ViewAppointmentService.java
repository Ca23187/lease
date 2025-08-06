package com.lease.web.app.service;

import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.lease.web.app.vo.appointment.AppointmentItemVo;

import java.util.List;

/**
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service
*/
public interface ViewAppointmentService {
    void saveOrUpdate(ViewAppointment viewAppointment);

    List<AppointmentItemVo> listItem(Long userId);

    AppointmentDetailVo getDetailById(Long id);
}
