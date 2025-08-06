package com.lease.web.app.repository;

import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.vo.appointment.AppointmentItemVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewAppointmentRepository extends JpaRepository<ViewAppointment, Long> {

    @Query("""
    select new com.lease.web.app.vo.appointment.AppointmentItemVo(
        ai.id,
        va.id,
        ai.name,
        va.appointmentTime,
        va.appointmentStatus
    )
    from ViewAppointment va
    left join ApartmentInfo ai on va.apartmentInfo.id = ai.id
    """)
    List<AppointmentItemVo> findItemsByUserId(Long userId);

    @Query("""
    select va
    from ViewAppointment va
    left join fetch va.apartmentInfo ai
    left join fetch ai.labelInfoList
    where va.id = :id
    """)
    ViewAppointment findWithApartmentInfoById(Long id);
}
