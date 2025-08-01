package com.lease.web.admin.service.impl;

import com.lease.model.entity.ViewAppointment;
import com.lease.model.enums.AppointmentStatus;
import com.lease.web.admin.dto.appointment.AppointmentQueryDto;
import com.lease.web.admin.repository.ViewAppointmentRepository;
import com.lease.web.admin.service.ViewAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 */
@Service
public class ViewAppointmentServiceImpl implements ViewAppointmentService {

    private final ViewAppointmentRepository viewAppointmentRepository;

    @Autowired
    public ViewAppointmentServiceImpl(ViewAppointmentRepository viewAppointmentRepository) {
        this.viewAppointmentRepository = viewAppointmentRepository;
    }

    @Transactional
    @Override
    public void updateStatusById(Long id, AppointmentStatus status) {
        viewAppointmentRepository.updateStatusById(id, status);
    }

    @Override
    public Page<ViewAppointment> pageViewAppointments(AppointmentQueryDto queryDto, Pageable pageable) {
        Page<ViewAppointment> page = viewAppointmentRepository.findByQuery(
                queryDto.getProvinceId(),
                queryDto.getCityId(),
                queryDto.getDistrictId(),
                queryDto.getApartmentId(),
                queryDto.getName(),
                queryDto.getPhone(),
                pageable
        );
        for (ViewAppointment viewAppointment: page.getContent()) {
            viewAppointment.setApartmentId(viewAppointment.getApartmentInfo().getId());
        }
        return page;
    }
}




