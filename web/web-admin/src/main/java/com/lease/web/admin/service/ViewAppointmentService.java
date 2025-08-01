package com.lease.web.admin.service;

import com.lease.model.entity.ViewAppointment;
import com.lease.model.enums.AppointmentStatus;
import com.lease.web.admin.dto.appointment.AppointmentQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service
*/
public interface ViewAppointmentService {

    void updateStatusById(Long id, AppointmentStatus status);

    Page<ViewAppointment> pageViewAppointments(AppointmentQueryDto queryDto, Pageable pageable);
}
